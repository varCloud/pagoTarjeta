package com.bluecloude.pagotarjeta;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bluecloude.pagotarjeta.AdaptersPagoT.AdapterMetodosPago;
import com.bluecloude.pagotarjeta.EntidadesPagoT.ClientePagoT;
import com.bluecloude.pagotarjeta.EntidadesPagoT.PaymentMethod;
import com.bluecloude.pagotarjeta.EntidadesPagoT.ResponseBrain.ResponseEliminarMetodoPago;
import com.bluecloude.pagotarjeta.ServiciosWeb.AgentePagoT;
import com.bluecloude.pagotarjeta.ServiciosWeb.PagoTAPI;
import com.bluecloude.pagotarjeta.Utilerias.SharedPreferencesManager;
import com.bluecloude.pagotarjeta.Utilerias.ToastManager;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.Card;
import com.braintreepayments.api.PayPal;
import com.braintreepayments.api.UnionPay;
import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
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
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements PaymentMethodNonceCreatedListener  {

    private static final int DROP_IN_REQUEST = 1;
    private static final String EXTRA_AUTHORIZATION = "com.bluecloude.pagotarjeta.EXTRA_AUTHORIZATION";
    private static final String EXTRA_CUSTOMER_ID = "com.bluecloude.pagotarjeta.EXTRA_CUSTOMER_ID";
    private String CustomerId ="288868914";
    private String  clientToken="";
    private Context context;
    private CardType mCardType;
    private CardForm mCardForm;
    protected BraintreeFragment mBraintreeFragment;
    private ProgressDialog mLoading;
    private String IdClienteBrain;
    private String TokenBrain;
    private PagoTAPI servicio ;
    private List<PaymentMethod> LstMetodosDePagos;
    AdapterMetodosPago adapterMetodosPago = null;
    View parentLayout;




    @BindView(R.id.recycler_view_metodos_pagos)
    RecyclerView recycler_view_metodos_pagos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parentLayout = findViewById(android.R.id.content);
        ButterKnife.bind(this);
        SharedPreferencesManager.init(this);
        servicio = AgentePagoT.getClient().create(PagoTAPI.class);
        this.context = this;
        ToastManager.init(context);

        try {
            TokenBrain  = SharedPreferencesManager.getValor("TokenBrain");
            IdClienteBrain  = SharedPreferencesManager.getValor("IdClienteBrain");
            ObtenerMetodosDePago();
            Button btn = (Button) findViewById(R.id.btnDropop);
            Button btnAgregarTarjeta = (Button) findViewById(R.id.btnAgregarTarjeta);

            initDeleteRecycler();
            btnAgregarTarjeta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        CardBuilder cardBuilder = new CardBuilder()
                                .cardNumber("378282246310005")
                                .expirationMonth("06")
                                .expirationYear("22");
                        Card.tokenize(mBraintreeFragment, cardBuilder);
                    }catch (Exception ex)
                    {
                        Log.e("error" , ex.getMessage());
                    }
                }



            });
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RequestParams params = new RequestParams();
                    params.put("CustomerId", CustomerId);
                    AsyncHttpClient client = new AsyncHttpClient();
                    //client.get("http://10.0.2.2/pagoTarjeta/braintree_slim_example-master/token", new TextHttpResponseHandler() {
                    client.post("http://192.168.5.178/pagoTarjeta/braintree_slim_example-master/tokenPorCliente",params, new TextHttpResponseHandler() {
                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String clientToken) {
                            clientToken = clientToken;
                            try {
                                mBraintreeFragment = BraintreeFragment.newInstance(MainActivity.this, clientToken);
                            } catch (InvalidArgumentException e) {
                                e.printStackTrace();
                            }
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

    @Override
    public void onRestart() {
        super.onRestart();
        TokenBrain  = SharedPreferencesManager.getValor("TokenBrain");
        IdClienteBrain  = SharedPreferencesManager.getValor("IdClienteBrain");
        ObtenerMetodosDePago();
    }
    public  void ObtenerMetodosDePago(){
        try{
            if(!IdClienteBrain.equals("")) {
                Log.d("IdClienteBrain",IdClienteBrain);
                servicio.ObtenerCliente(IdClienteBrain).enqueue(new Callback<ClientePagoT>() {
                    @Override
                    public void onResponse(Call<ClientePagoT> call, Response<ClientePagoT> response) {
                        if (response.body().getPaymentMethods().size() > 0) {
                            LstMetodosDePagos = response.body().getPaymentMethods();
                            recycler_view_metodos_pagos.setHasFixedSize(true);
                            LinearLayoutManager llmanager = new LinearLayoutManager(context);
                            llmanager.setOrientation(LinearLayoutManager.VERTICAL);
                            recycler_view_metodos_pagos.setLayoutManager(llmanager);

                            adapterMetodosPago  = new AdapterMetodosPago(context, response.body().getPaymentMethods());
                            recycler_view_metodos_pagos.setAdapter(adapterMetodosPago);
                            UctualizarAnimacionRecicler();

                        }
                    }

                    @Override
                    public void onFailure(Call<ClientePagoT> call, Throwable t) {
                        Log.d("ErrorMetodo", t.getMessage());
                    }
                });
            }

        }catch (Exception ex)
        {
            Log.d("Error ObtenerMetodos" , ex.getMessage());
        }
    }

    @OnClick(value = R.id.btnCrearCliente)
    public void ClickbtnCrearCliente(View v)
    {
        try{
            IdClienteBrain = SharedPreferencesManager.getValor("IdClienteBrain");
            if (IdClienteBrain == "") {
                ClientePagoT obj = new ClientePagoT();
                obj.setNombre("Victor Adrian");
                obj.setApellido("Reyes");
                obj.setMail("var901106@gmail.com");
                obj.setTelefono("4433740472");

                servicio.CrearCliente(obj.getNombre(), obj.getApellido(), obj.getMail(), obj.getTelefono()).enqueue(new Callback<ClientePagoT>() {
                    @Override
                    public void onResponse(Call<ClientePagoT> call, Response<ClientePagoT> response) {
                        SharedPreferencesManager.setValor("Registrado", "true");
                        SharedPreferencesManager.setValor("IdClienteBrain", response.body().getIdClienteBrain());
                        IdClienteBrain = response.body().getIdClienteBrain();
                        obj.setId(response.body().getIdClienteBrain());
                        ObtenerTokenCliente();
                    }

                    @Override
                    public void onFailure(Call<ClientePagoT> call, Throwable t) {
                        Log.d("OnFailureCrearCliente", t.getMessage());
                    }
                });
            }else {
                Log.d("IdClienteBrain", IdClienteBrain.toString());
                //IdClienteBrain = SharedPreferencesManager.getValor("IdClienteBrain");
                ObtenerTokenCliente();
            }

        }catch(Exception ex)
        {
            Log.d("Error en click", ex.getMessage());
        }
    }

    public void ObtenerTokenCliente()
    {
        try{

              servicio.tokenPorCliente(IdClienteBrain).enqueue(new Callback<ClientePagoT>() {
                  @Override
                  public void onResponse(Call<ClientePagoT> call, Response<ClientePagoT> response) {
                      SharedPreferencesManager.setValor("TokenBrain", response.body().getTokenBrain());
                  }

                  @Override
                  public void onFailure(Call<ClientePagoT> call, Throwable t) {
                      Log.d("OnFailureTokenCliente", t.getMessage());
                  }
              });


//            RequestParams params = new RequestParams();
//            params.put("CustomerId", CustomerId);
//            AsyncHttpClient client = new AsyncHttpClient();
//            //client.get("http://10.0.2.2/pagoTarjeta/braintree_slim_example-master/token", new TextHttpResponseHandler() {
//            client.post("http://192.168.5.178/pagoTarjeta/braintree_slim_example-master/tokenPorCliente",params, new TextHttpResponseHandler() {
//                @Override
//                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//
//                }
//
//                @Override
//                public void onSuccess(int statusCode, Header[] headers, String clientToken) {
//                    clientToken = clientToken;
//                    try {
//                        mBraintreeFragment = BraintreeFragment.newInstance(MainActivity.this, clientToken);
//                    } catch (InvalidArgumentException e) {
//                        e.printStackTrace();
//                    }
//
//                    //startActivityForResult(getDropInRequest().getIntent(context), DROP_IN_REQUEST);
//                    //Braintree braintree = Braintree.getInstance(this, clientToken);
//                }
//            });

        }catch (Exception ex)
        {
            Log.e("error", ex.getMessage());
        }
    }

    private DropInRequest getDropInRequest() {
        DropInRequest dropInRequest = new DropInRequest()
                .amount("1.00")
                .clientToken(clientToken)
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

    @Override
    public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentMethodNonce) {
        String nonce = paymentMethodNonce.getNonce();
        try{
            RequestParams params = new RequestParams();
            params.put("CustomerId", CustomerId);
            params.put("nonceFromTheClient", nonce);
            AsyncHttpClient client = new AsyncHttpClient();
            //client.get("http://10.0.2.2/pagoTarjeta/braintree_slim_example-master/token", new TextHttpResponseHandler() {
            client.post("http://192.168.5.178/pagoTarjeta/braintree_slim_example-master/crearMetodoPago",params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.e("error", responseString);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    Log.e("onSuccess", responseString);

                    //startActivityForResult(getDropInRequest().getIntent(context), DROP_IN_REQUEST);
                    //Braintree braintree = Braintree.getInstance(this, clientToken);
                }
            });
        }catch (Exception ex)
        {
            Log.e("error", ex.getMessage());
        }

    }


    @OnClick(R.id.btnCrearMetodoPago)
    void alCc(View view) {
        Intent myIntent = new Intent(MainActivity.this, AgregarTarjetaActivity.class);
        myIntent.putExtra("key", "valor"); //Optional parameters
        MainActivity.this.startActivity(myIntent);
    }

    @OnClick(value = R.id.btnCrearTransaccion)
    void alClickCrearTransaccion(View v)
    {
        try{
            UctualizarAnimacionRecicler();
            Intent myIntent = new Intent(MainActivity.this, TransaccionActivity.class);
            myIntent.putExtra("key", "valor"); //Optional parameters
            MainActivity.this.startActivity(myIntent);

        }catch(Exception ex)
        {
            Log.d("Error CrearT", ex.getMessage());
        }
    }


    public  void UctualizarAnimacionRecicler()
    {
        try{
            final Context context = recycler_view_metodos_pagos.getContext();
            final LayoutAnimationController controller =
                    AnimationUtils.loadLayoutAnimation(context, R.anim.adapter_layout_fall_down);

            recycler_view_metodos_pagos.setLayoutAnimation(controller);
            recycler_view_metodos_pagos.getAdapter().notifyDataSetChanged();
            recycler_view_metodos_pagos.scheduleLayoutAnimation();

        }catch(Exception ex)
        {
            Log.d("Error CrearT", ex.getMessage());
        }
    }


    void initDeleteRecycler()
    {

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                if (viewHolder instanceof AdapterMetodosPago.MyViewHolder) {
                    // get the removed item name to display it in snack bar
                    String name = LstMetodosDePagos.get(viewHolder.getAdapterPosition()).getCardType()+" "+LstMetodosDePagos.get(viewHolder.getAdapterPosition()).getLast4();

                    // backup of removed item for undo purpose
                    final PaymentMethod deletedItem = LstMetodosDePagos.get(viewHolder.getAdapterPosition());
                    final int deletedIndex = viewHolder.getAdapterPosition();

                    // remove the item from recycler view
                    //adapterMetodosPago.removeItem(viewHolder.getAdapterPosition());
                    EliminarMetodoPago(deletedItem,viewHolder.getAdapterPosition());

                }
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (viewHolder != null) {
                    final View foregroundView = ((AdapterMetodosPago.MyViewHolder) viewHolder).viewForeground;

                    getDefaultUIUtil().onSelected(foregroundView);
                }
            }
            @Override
            public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                        RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                        int actionState, boolean isCurrentlyActive) {
                final View foregroundView = ((AdapterMetodosPago.MyViewHolder) viewHolder).viewForeground;
                getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                        actionState, isCurrentlyActive);
            }


            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView,
                                    RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {
                final View foregroundView = ((AdapterMetodosPago.MyViewHolder) viewHolder).viewForeground;

                getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                        actionState, isCurrentlyActive);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                final View foregroundView = ((AdapterMetodosPago.MyViewHolder) viewHolder).viewForeground;
                getDefaultUIUtil().clearView(foregroundView);
            }

            @Override
            public int convertToAbsoluteDirection(int flags, int layoutDirection) {
                return super.convertToAbsoluteDirection(flags, layoutDirection);
            }

        };
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recycler_view_metodos_pagos);
    }

    public void EliminarMetodoPago(PaymentMethod item , int position )
    {
        try{
                servicio.EliminarMetodoPago(item.getToken()).enqueue(new Callback<ResponseEliminarMetodoPago>() {
                    @Override
                    public void onResponse(Call<ResponseEliminarMetodoPago> call, Response<ResponseEliminarMetodoPago> response) {
                            ToastManager.toastShort(response.body().getMensaje());
                            if(response.body().getEstatus()){
                                adapterMetodosPago.removeItem(position);
                                Snackbar snackbar = Snackbar
                                        .make(parentLayout, "Eliminado", Snackbar.LENGTH_LONG);
                                snackbar.setAction("Agregar metodo de pago", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        Intent myIntent = new Intent(MainActivity.this, AgregarTarjetaActivity.class);
                                        myIntent.putExtra("key", "valor"); //Optional parameters
                                        MainActivity.this.startActivity(myIntent);
                                    }
                                });
                                snackbar.setActionTextColor(Color.YELLOW);
                                snackbar.show();
                            }else
                                ToastManager.toastLong("Espere un momento y vuelva a intentarlo.");
                    }

                    @Override
                    public void onFailure(Call<ResponseEliminarMetodoPago> call, Throwable t) {
                        ToastManager.toastLong(t.getMessage());
                    }
                });
        }catch(Exception ex) {
            ToastManager.toastLong(ex.getMessage());
        }
    }


}
