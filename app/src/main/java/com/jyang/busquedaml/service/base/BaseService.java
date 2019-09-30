package com.jyang.busquedaml.service.base;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseService {
    protected Retrofit retrofit;

    private final int TIMEOUT_UNAUTHENTICATE = 10;
    public static final String BASE_URL = "https://api.mercadolibre.com/";

    public BaseService() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT_UNAUTHENTICATE, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_UNAUTHENTICATE, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();
    }
}
