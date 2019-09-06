package com.revolut.testapp.rest.entities;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenCurrency {
    @GET("latest")
    Call<CurrencyRequestRestModel> loadCurrency(@Query("base") String currency);




}
