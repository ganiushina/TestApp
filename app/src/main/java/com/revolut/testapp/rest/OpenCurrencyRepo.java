package com.revolut.testapp.rest;

import com.revolut.testapp.rest.entities.IOpenCurrency;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenCurrencyRepo {
    private static OpenCurrencyRepo singleton = null;
    private IOpenCurrency API;

    private OpenCurrencyRepo() {
        API = createAdapter();
    }

    public static OpenCurrencyRepo getSingleton() {
        if(singleton == null) {
            singleton = new OpenCurrencyRepo();
        }
        return singleton;
    }

    public IOpenCurrency getAPI() {
        return API;
    }

    private IOpenCurrency createAdapter() {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("https://revolut.duckdns.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return adapter.create(IOpenCurrency.class);
    }
}
