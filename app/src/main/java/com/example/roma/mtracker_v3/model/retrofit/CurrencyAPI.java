package com.example.roma.mtracker_v3.model.retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Roma on 17.07.2017.
 */

public interface CurrencyAPI {


    @GET("live")
    Call<DataCurrency> getData(@Query("access_key") String key, @Query("currencies") String currencys);

//    @GET("latest")
//    Call<Currency> getData(@Query("base") String rub , @Query("symbols") String currency);

//    @GET("latest?)
//    Call<List<Currency.Rates>> getData(@Query("base") String rub);
}




































