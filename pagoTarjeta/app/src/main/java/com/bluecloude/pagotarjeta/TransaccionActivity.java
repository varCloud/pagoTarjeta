package com.bluecloude.pagotarjeta;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bluecloude.pagotarjeta.AdaptersPagoT.AdapterMetodosPago;
import com.bluecloude.pagotarjeta.AdaptersPagoT.AdaterSpinnerMetodosPago;
import com.bluecloude.pagotarjeta.EntidadesPagoT.ClientePagoT;
import com.bluecloude.pagotarjeta.EntidadesPagoT.PaymentMethod;
import com.bluecloude.pagotarjeta.EntidadesPagoT.ResponseBrain.ResponseCrearTransaccionMetodoPago;
import com.bluecloude.pagotarjeta.ServiciosWeb.AgentePagoT;
import com.bluecloude.pagotarjeta.ServiciosWeb.PagoTAPI;
import com.bluecloude.pagotarjeta.Utilerias.SharedPreferencesManager;
import com.braintreepayments.api.BraintreeFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaccionActivity extends AppCompatActivity {

    @BindView(R.id.spMetodosPagoT)
    Spinner spMetodosPagoT;
    @BindView(R.id.txtMonto)
    EditText txtMonto;
    private BraintreeFragment mBraintreeFragment;
    private Context context;
    private  String TokenBrain;
    private String IdClienteBrain;
    private String tokenMetodoPago;
    private PagoTAPI servicio ;
    private List<PaymentMethod> metodosPago;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaccion);
        ButterKnife.bind(this);
        context  = this;
        servicio = AgentePagoT.getClient().create(PagoTAPI.class);
        try{
            TokenBrain  = SharedPreferencesManager.getValor("TokenBrain");
            IdClienteBrain  = SharedPreferencesManager.getValor("IdClienteBrain");
            ObtenerMetodosDePago();
        }catch (Exception ex)
        {
            throw  ex;
        }
    }

    public  void ObtenerMetodosDePago(){
        try{
            if(!IdClienteBrain.equals("")) {
                Log.d("IdClienteBrain",IdClienteBrain);
                servicio.ObtenerCliente(IdClienteBrain).enqueue(new Callback<ClientePagoT>() {
                    @Override
                    public void onResponse(Call<ClientePagoT> call, Response<ClientePagoT> response) {
                        if (response.body().getPaymentMethods().size() > 0) {
                            metodosPago  = response.body().getPaymentMethods();
                            AdaterSpinnerMetodosPago adapterSP  = new AdaterSpinnerMetodosPago(context,response.body().getPaymentMethods());
                            spMetodosPagoT.setAdapter(adapterSP);
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

    @OnItemSelected(R.id.spMetodosPagoT)
    void OnSelectspMetodosPagoT(Spinner sp ,  int position)
    {
        try
        {
            tokenMetodoPago = this.metodosPago.get(position).getToken();
            Toast.makeText(this, this.metodosPago.get(position).getCardType(), Toast.LENGTH_SHORT).show();

        }catch (Exception ex)
        {
            throw  ex;
        }
    }

    @OnClick(value = R.id.btnCrearTransaccion)
    void OnClickbtnCrearTransaccion(View v)
    {
        try{
            servicio.CrearTransaccion(txtMonto.getText().toString() , tokenMetodoPago).enqueue(new Callback<ResponseCrearTransaccionMetodoPago>() {
                @Override
                public void onResponse(Call<ResponseCrearTransaccionMetodoPago> call, Response<ResponseCrearTransaccionMetodoPago> response) {
                    if  (response.body().getSuccess()){
                        Log.d("Response" , "Pago Realizado");
                    }
                }

                @Override
                public void onFailure(Call<ResponseCrearTransaccionMetodoPago> call, Throwable t) {
                    Log.d("Error Pagar" , t.getMessage());
                }
            });
        }catch (Exception ex)
        {
            Log.d("Error Pagar" , ex.getMessage());
        }

    }
}

