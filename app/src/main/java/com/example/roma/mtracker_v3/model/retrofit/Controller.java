package com.example.roma.mtracker_v3.model.retrofit;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Roma on 17.07.2017.
 */

public class Controller {
//    static final String BASE_URL = "http://api.fixer.io/";
    static final String BASE_URL = "http://apilayer.net/api/";
    public static final String API_KEY = "0d9b2fc39b91668d893059fe19cb3019";
    public static final String API_CUR = "EUR,GBP,RUB";

    static CurrencyAPI currencyAPI;


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


   private static Retrofit.Builder sRetrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = sRetrofit
                .client(httpClient.build())
                .build();

        return retrofit.create(serviceClass);

    }




}
