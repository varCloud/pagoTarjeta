package com.bluecloude.pagotarjeta.ServiciosWeb;

import com.bluecloude.pagotarjeta.EntidadesPagoT.ClientePagoT;
import com.bluecloude.pagotarjeta.EntidadesPagoT.ResponseBrain.ResponseAgregarMetodoPago;
import com.bluecloude.pagotarjeta.EntidadesPagoT.ResponseBrain.ResponseCrearTransaccionMetodoPago;
import com.bluecloude.pagotarjeta.EntidadesPagoT.ResponseBrain.ResponseEliminarMetodoPago;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PagoTAPI {

    @POST("changes/")
    void  loadChanges(@Query("q") String status);

    @FormUrlEncoded
    @POST("CrearCliente")
    Call<ClientePagoT> CrearCliente(@Field("Nombre") String Nombre ,@Field("Apellido") String Apellido ,@Field("Mail") String Mail ,@Field("Telefono") String Telefono  );

    @FormUrlEncoded
    @POST("TokenPorCliente")
    Call<ClientePagoT> tokenPorCliente(@Field("IdClienteBrain") String IdClienteBrain  );


    @FormUrlEncoded
    @POST("CrearMetodoPago")
    Call<ResponseAgregarMetodoPago> CrearMetodoPago(@Field("IdClienteBrain") String IdClienteBrain , @Field("NonceFromTheClient") String NonceFromTheClient  );

    @FormUrlEncoded
    @POST("EliminarMetodoPago")
    Call<ResponseEliminarMetodoPago> EliminarMetodoPago(@Field("paymentMethodToken") String paymentMethodToken  );


    @FormUrlEncoded
    @POST("ObtenerCliente")
    Call<ClientePagoT> ObtenerCliente(@Field("IdClienteBrain") String IdClienteBrain);

    @FormUrlEncoded
    @POST("CrearTransaccion")
    Call<ResponseCrearTransaccionMetodoPago> CrearTransaccion(@Field("amount") String amount , @Field("paymentMethodToken") String paymentMethodToken );

}
