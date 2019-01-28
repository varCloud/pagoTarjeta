package com.bluecloude.pagotarjeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bluecloude.pagotarjeta.EntidadesPagoT.ClientePagoT;
import com.bluecloude.pagotarjeta.EntidadesPagoT.ExceptionsBrain.ErrorAgregarTarjeta;
import com.bluecloude.pagotarjeta.EntidadesPagoT.ResponseBrain.ResponseAgregarMetodoPago;
import com.bluecloude.pagotarjeta.ServiciosWeb.AgentePagoT;
import com.bluecloude.pagotarjeta.ServiciosWeb.PagoTAPI;
import com.bluecloude.pagotarjeta.Utilerias.SharedPreferencesManager;
import com.bluecloude.pagotarjeta.Utilerias.ToastManager;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.Card;
import com.braintreepayments.api.exceptions.ErrorWithResponse;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.CardBuilder;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarTarjetaActivity extends AppCompatActivity implements PaymentMethodNonceCreatedListener,BraintreeErrorListener {

    private static final int CARD_NUMBER_TOTAL_SYMBOLS = 19; // size of pattern 0000-0000-0000-0000
    private static final int CARD_NUMBER_TOTAL_DIGITS = 16; // max numbers of digits in pattern: 0000 x 4
    private static final int CARD_NUMBER_DIVIDER_MODULO = 5; // means divider position is every 5th symbol beginning with 1
    private static final int CARD_NUMBER_DIVIDER_POSITION = CARD_NUMBER_DIVIDER_MODULO - 1; // means divider position is every 4th symbol beginning with 0
    private static final char CARD_NUMBER_DIVIDER = '-';

    private static final int CARD_DATE_TOTAL_SYMBOLS = 5; // size of pattern MM/YY
    private static final int CARD_DATE_TOTAL_DIGITS = 4; // max numbers of digits in pattern: MM + YY
    private static final int CARD_DATE_DIVIDER_MODULO = 3; // means divider position is every 3rd symbol beginning with 1
    private static final int CARD_DATE_DIVIDER_POSITION = CARD_DATE_DIVIDER_MODULO - 1; // means divider position is every 2nd symbol beginning with 0
    private static final char CARD_DATE_DIVIDER = '/';

    private static final int CARD_CVC_TOTAL_SYMBOLS = 3;
    private static final int MY_SCAN_REQUEST_CODE = 200;

    BraintreeFragment mBraintreeFragment;
    private  String TokenBrain;
    private String IdClienteBrain;
    private PagoTAPI servicio ;
    private ToastManager toastManager;

    @BindView(R.id.txtAgregarNumTarjeta)
    TextView txtAgregarNumTarjeta;

    @BindView(R.id.txtAgrearFechaTarjeta)
    TextView txtAgrearFechaTarjeta;

    @BindView(R.id.txtAgregarCVCTarjeta)
    TextView txtAgregarCVCTarjeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tarjeta);
        ButterKnife.bind(this);
        SharedPreferencesManager.init(this);
        servicio = AgentePagoT.getClient().create(PagoTAPI.class);
        try {
            TokenBrain  = SharedPreferencesManager.getValor("TokenBrain");
            IdClienteBrain  = SharedPreferencesManager.getValor("IdClienteBrain");
            toastManager.init(this);
            if(!TokenBrain.equals("")) {
                mBraintreeFragment = BraintreeFragment.newInstance(AgregarTarjetaActivity.this, TokenBrain);
            }

            txtAgregarNumTarjeta.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!isInputCorrect(s, CARD_NUMBER_TOTAL_SYMBOLS, CARD_NUMBER_DIVIDER_MODULO, CARD_NUMBER_DIVIDER)) {
                        s.replace(0, s.length(), concatString(getDigitArray(s, CARD_NUMBER_TOTAL_DIGITS), CARD_NUMBER_DIVIDER_POSITION, CARD_NUMBER_DIVIDER));
                    }
                }
            });
        }catch (Exception ex){
            Log.d("Error  AgregarTarjeta" , ex.getMessage());
        }
    }
    // eso es igual a declararlo con android puro aqui se usa ButterKnife
    @OnTextChanged(value = R.id.txtAgrearFechaTarjeta , callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    protected void onCardDateTextChanged(Editable s) {
        if (!isInputCorrect(s, CARD_DATE_TOTAL_SYMBOLS, CARD_DATE_DIVIDER_MODULO, CARD_DATE_DIVIDER)) {
            s.replace(0, s.length(), concatString(getDigitArray(s, CARD_DATE_TOTAL_DIGITS), CARD_DATE_DIVIDER_POSITION, CARD_DATE_DIVIDER));
        }
    }

    @OnTextChanged(value = R.id.txtAgregarCVCTarjeta , callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    protected void onCardCVCTextChanged(Editable s) {
        if (s.length() > CARD_CVC_TOTAL_SYMBOLS) {
            s.delete(CARD_CVC_TOTAL_SYMBOLS, s.length());
        }
    }

    @OnClick(value= R.id.btnAgregarT)
    public  void  ClickbtnAgregarT (View v)
    {
        try{
            CardBuilder cardBuilder = new CardBuilder()
                    .cardNumber(txtAgregarNumTarjeta.getText().toString().replace("-",""))
                    .expirationMonth(txtAgrearFechaTarjeta.getText().toString().split("/")[0])
                    .expirationYear(txtAgrearFechaTarjeta.getText().toString().split("/")[1])
                    .cvv(txtAgregarCVCTarjeta.getText().toString())
                    ;

            Card.tokenize(mBraintreeFragment, cardBuilder);


        }catch (Exception ex)
        {
            Log.d("Error AgregarT", ex.getMessage());
        }
    }

    @OnClick(value = R.id.ivCamra)
    public void ClickImageCamera (View view) {
        Intent scanIntent = new Intent(this, CardIOActivity.class);

        // customize these values to suit your needs.
        // customize these values to suit your needs.
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, true); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true); // hide logo
        scanIntent.putExtra(CardIOActivity.EXTRA_USE_PAYPAL_ACTIONBAR_ICON,false); // hide PayPal logo
        /**
         * Boolean extra. Optional. If this value is set to <code>true</code> the user will not be prompted to
         * confirm their card number after processing.
         */
        scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_CONFIRMATION ,true);
        scanIntent.putExtra(CardIOActivity.EXTRA_UNBLUR_DIGITS ,true);
        scanIntent.putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY,true); //scan card’s expiry date

        // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_SCAN_REQUEST_CODE) {
            String resultDisplayStr;
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
                resultDisplayStr = "Card Number: " + scanResult.getRedactedCardNumber() + "\n";
                txtAgregarNumTarjeta.setText(scanResult.getRedactedCardNumber());
                // Do something with the raw number, e.g.:
                // myService.setCardNumber( scanResult.cardNumber );

                if (scanResult.isExpiryValid()) {
                    resultDisplayStr += "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n";
                }

                if (scanResult.cvv != null) {
                    // Never log or display a CVV
                    resultDisplayStr += "CVV has " + scanResult.cvv.length() + " digits.\n";
                }

                if (scanResult.postalCode != null) {
                    resultDisplayStr += "Postal Code: " + scanResult.postalCode + "\n";
                }
            }
            else {
                resultDisplayStr = "Scan was canceled.";
            }
            Log.d("Resulta Scan",resultDisplayStr);
            // do something with resultDisplayStr, maybe display it in a textView
            // resultTextView.setText(resultDisplayStr);
        }
    }

    private boolean isInputCorrect(Editable s, int size, int dividerPosition, char divider) {
        boolean isCorrect = s.length() <= size;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && (i + 1) % dividerPosition == 0) {
                isCorrect &= divider == s.charAt(i);
            } else {
                isCorrect &= Character.isDigit(s.charAt(i));
            }
        }
        return isCorrect;
    }

    private String concatString(char[] digits, int dividerPosition, char divider) {
        final StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != 0) {
                formatted.append(digits[i]);
                if ((i > 0) && (i < (digits.length - 1)) && (((i + 1) % dividerPosition) == 0)) {
                    formatted.append(divider);
                }
            }
        }

        return formatted.toString();
    }

    private char[] getDigitArray(final Editable s, final int size) {
        char[] digits = new char[size];
        int index = 0;
        for (int i = 0; i < s.length() && index < size; i++) {
            char current = s.charAt(i);
            if (Character.isDigit(current)) {
                digits[index] = current;
                index++;
            }
        }
        return digits;
    }



    @Override
    public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentMethodNonce) {
        try{
            String nonce = paymentMethodNonce.getNonce();
            servicio.CrearMetodoPago(IdClienteBrain,nonce).enqueue(new Callback<ResponseAgregarMetodoPago>() {
                @Override
                public void onResponse(Call<ResponseAgregarMetodoPago> call, Response<ResponseAgregarMetodoPago> response) {
                    Log.d("VerificacionT" , response.body().getSuccess().toString());
                    if(response.body().getSuccess()){
                        if(response.body().getPaymentMethod().getVerifications().get(0).getStatus().toString().equals("verified")
                            && response.body().getPaymentMethod().getVerifications().get(0).getCvvResponseCode().equals("M"))
                            {
                              finish();
                            }
                        }
                        else
                        {
                            ToastManager.toastShort("No pudimos verificar su tarjeta, por favor verifiquela.");
                        }
                    }

                @Override
                public void onFailure(Call<ResponseAgregarMetodoPago> call, Throwable t) {
                    Log.d("onFailureVerificacionT" , t.getMessage());
                }
            });

        }catch (Exception ex)
        {
            Log.d("Error AgregarT", ex.getMessage());
        }
    }

    @Override
    public void onError(Exception error) {
        if (error instanceof ErrorWithResponse) {
            ErrorAgregarTarjeta errorResponse =  new Gson().fromJson(((ErrorWithResponse) error).getErrorResponse(),ErrorAgregarTarjeta.class);
            Log.d("Error brain",error.getMessage());
            Log.d("Error brain",((ErrorWithResponse) error).getErrorResponse());
            Log.d("Error brain",((ErrorWithResponse) error).getStatusCode()+"");
              toastManager.toastShort(errorResponse.getErrors().get(0).getMessage());
        }
    }
}
