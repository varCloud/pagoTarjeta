package com.bluecloude.pagotarjeta.ServiciosWeb;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgentePagoT {

    private static Retrofit retrofit = null;

    private static String rutaConEmulador = "http://10.0.2.2:8080/pagoTarjeta/braintree_slim_example-master/";
    private static String rutacel= "http://localhost:8080/pagoTarjeta/braintree_slim_example-master/";

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(rutaConEmulador)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();



        return retrofit;
    }

}
