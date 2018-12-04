package com.bluecloude.pagotarjeta;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.Card;
import com.braintreepayments.api.PayPal;
import com.braintreepayments.api.UnionPay;
import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.interfaces.BraintreeCancelListener;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.BraintreePaymentResultListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.Authorization;
import com.braintreepayments.api.models.BraintreePaymentResult;
import com.braintreepayments.api.models.CardBuilder;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.cardform.OnCardFormFieldFocusedListener;
import com.braintreepayments.cardform.OnCardFormSubmitListener;
import com.braintreepayments.cardform.utils.CardType;
import com.braintreepayments.cardform.view.CardEditText;
import com.braintreepayments.cardform.view.CardForm;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity  {

    private static final int DROP_IN_REQUEST = 1;
    private static final String EXTRA_AUTHORIZATION = "com.braintreepayments.demo.EXTRA_AUTHORIZATION";
    private static final String EXTRA_CUSTOMER_ID = "com.braintreepayments.demo.EXTRA_CUSTOMER_ID";
    private String token ="";
    private Context context;
    private CardType mCardType;
    private CardForm mCardForm;
    protected BraintreeFragment mBraintreeFragment;
    private ProgressDialog mLoading;


    protected String mAuthorization;
    @BindView(R.id.txtNotarjeta)
    TextView txtNotarjeta;
    @BindView(R.id.txtFechaVen) TextView txtFechaVen;
    @BindView(R.id.txtCVV) TextView txtCVV;
    @BindView(R.id.txtCP) TextView txtCP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        try {

            Button btn = (Button) findViewById(R.id.btnDropop);
            Button btnAgregarTarjeta = (Button) findViewById(R.id.btnAgregarTarjeta);
            mBraintreeFragment = BraintreeFragment.newInstance(this, mAuthorization);
            this.context = this;
            btnAgregarTarjeta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CardBuilder cardBuilder = new CardBuilder()
                            .cardNumber(txtNotarjeta.getText().toString())
                            .expirationMonth("06")
                            .expirationYear("22");
                    Card.tokenize(mBraintreeFragment,cardBuilder);
                }



            });
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AsyncHttpClient client = new AsyncHttpClient();
                    //client.get("http://10.0.2.2/pagoTarjeta/braintree_slim_example-master/token", new TextHttpResponseHandler() {
                    client.get("http://192.168.5.178/pagoTarjeta/braintree_slim_example-master/token", new TextHttpResponseHandler() {
                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String clientToken) {
                            token = clientToken;


                            //startActivityForResult(getDropInRequest().getIntent(context), DROP_IN_REQUEST);
                            //Braintree braintree = Braintree.getInstance(this, clientToken);
                        }
                    });

                }
            });
        }catch (Exception ex)
        {
            Log.d("Error" , ex.getMessage());
        }
    }

    private DropInRequest getDropInRequest() {
        DropInRequest dropInRequest = new DropInRequest()
                .amount("1.00")
                .clientToken(token)
                .collectDeviceData(false)
                .requestThreeDSecureVerification(false)
                //.androidPayCart()true)
                .androidPayShippingAddressRequired(false)
                .androidPayPhoneNumberRequired(false);
                //.androidPayAllowedCountriesForShipping()false);

//        if (Settings.isPayPalAddressScopeRequested(this)) {
////            dropInRequest.paypalAdditionalScopes(Collections.singletonList(PayPal.SCOPE_ADDRESS));
////        }

        return dropInRequest;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DROP_IN_REQUEST) {
            if (resultCode == RESULT_OK) {
                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                String paymentNonce = result.getPaymentMethodNonce().getNonce();
                // use the result to update your UI and send the payment method nonce to your server
            } else if (resultCode == RESULT_CANCELED) {
                // the user canceled
            } else {
                // handle errors here, an exception may be available in
                Exception error = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
            }
        }
    }


}
