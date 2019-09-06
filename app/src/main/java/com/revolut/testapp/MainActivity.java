package com.revolut.testapp;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.revolut.testapp.rest.OpenCurrencyRepo;
import com.revolut.testapp.rest.entities.CurrencyRequestRestModel;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Currency> currencies = new ArrayList<>();
    CurrencyAdapter currencyAdapter;

    public static final String BROADCAST_ACTION = "com.revolut.testapp.service";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private Handler handler = new Handler();
    private Runnable task = new Runnable() {
        @Override
        public void run() {
            String currency = "EUR";
            updateCurrenciesData(currency);
            handler.postDelayed(this, 1000);
        }
    };

    protected void onResume() {
        super.onResume();
        handler.postDelayed(task, 1000);
    }

    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(task);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        currencyAdapter = new CurrencyAdapter(currencies);
        recyclerView.setAdapter(currencyAdapter);
    }

    private void addCurrencies(CurrencyRequestRestModel model) {
        currencies.add(new Currency("AUD", model.rates.AUD));
        currencies.add(new Currency("BGN", model.rates.BGN));
        currencies.add(new Currency("BRL", model.rates.BRL));
        currencies.add(new Currency("CAD", model.rates.CAD));
        currencies.add(new Currency("CHF", model.rates.CHF));
        currencies.add(new Currency("CNY", model.rates.CNY));
        currencies.add(new Currency("CZK", model.rates.CZK));
        currencies.add(new Currency("DKK", model.rates.DKK));
        currencies.add(new Currency("GBP", model.rates.GBP));
        currencies.add(new Currency("HKD", model.rates.HKD));
        currencies.add(new Currency("HRK", model.rates.HRK));
        currencies.add(new Currency("HUF", model.rates.HUF));
        currencies.add(new Currency("IDR", model.rates.IDR));
        currencies.add(new Currency("ILS", model.rates.ILS));
        currencies.add(new Currency("INR", model.rates.INR));
        currencies.add(new Currency("ISK", model.rates.ISK));
        currencies.add(new Currency("JPY", model.rates.JPY));
        currencies.add(new Currency("KRW", model.rates.KRW));
        currencies.add(new Currency("MXN", model.rates.MXN));
        currencies.add(new Currency("MYR", model.rates.MYR));
        currencies.add(new Currency("NOK", model.rates.NOK));
        currencies.add(new Currency("NZD", model.rates.NZD));
        currencies.add(new Currency("PHP", model.rates.PHP));
        currencies.add(new Currency("PLN", model.rates.PLN));
        currencies.add(new Currency("RON", model.rates.RON));
        currencies.add(new Currency("RUB", model.rates.RUB));
        currencies.add(new Currency("SEK", model.rates.SEK));
        currencies.add(new Currency("SGD", model.rates.SGD));
        currencies.add(new Currency("THB", model.rates.THB));
        currencies.add(new Currency("TRY", model.rates.TRY));
        currencies.add(new Currency("USD", model.rates.USD));
        currencies.add(new Currency("ZAR", model.rates.ZAR));
    }

    private void updateCurrenciesData(String currency) {
        OpenCurrencyRepo.getSingleton().getAPI().loadCurrency(currency)
                .enqueue(new Callback<CurrencyRequestRestModel>() {
                    @Override
                    public void onResponse(@NonNull Call<CurrencyRequestRestModel> call,
                                           @NonNull final Response<CurrencyRequestRestModel> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            if (currencies.isEmpty()) {
                                addCurrencies(response.body());
                            } else {
                                final ArrayList<Currency> currencyArrayList = new ArrayList<>();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        currencyArrayList.add(new Currency("AUD", response.body().rates.AUD));
                                        currencyArrayList.add(new Currency("BGN", response.body().rates.BGN));
                                        currencyArrayList.add(new Currency("BRL", response.body().rates.BRL));
                                        currencyArrayList.add(new Currency("CAD", response.body().rates.CAD));
                                        currencyArrayList.add(new Currency("CHF", response.body().rates.CHF));
                                        currencyArrayList.add(new Currency("CNY", response.body().rates.CNY));
                                        currencyArrayList.add(new Currency("CZK", response.body().rates.CZK));
                                        currencyArrayList.add(new Currency("DKK", response.body().rates.DKK));
                                        currencyArrayList.add(new Currency("GBP", response.body().rates.GBP));
                                        currencyArrayList.add(new Currency("HKD", response.body().rates.HKD));
                                        currencyArrayList.add(new Currency("HRK", response.body().rates.HRK));
                                        currencyArrayList.add(new Currency("HUF", response.body().rates.HUF));
                                        currencyArrayList.add(new Currency("IDR", response.body().rates.IDR));
                                        currencyArrayList.add(new Currency("ILS", response.body().rates.ILS));
                                        currencyArrayList.add(new Currency("INR", response.body().rates.INR));
                                        currencyArrayList.add(new Currency("ISK", response.body().rates.ISK));
                                        currencyArrayList.add(new Currency("JPY", response.body().rates.JPY));
                                        currencyArrayList.add(new Currency("KRW", response.body().rates.KRW));
                                        currencyArrayList.add(new Currency("MXN", response.body().rates.MXN));
                                        currencyArrayList.add(new Currency("MYR", response.body().rates.MYR));
                                        currencyArrayList.add(new Currency("NOK", response.body().rates.NOK));
                                        currencyArrayList.add(new Currency("NZD", response.body().rates.NZD));
                                        currencyArrayList.add(new Currency("PHP", response.body().rates.PHP));
                                        currencyArrayList.add(new Currency("PLN", response.body().rates.PLN));
                                        currencyArrayList.add(new Currency("RON", response.body().rates.RON));
                                        currencyArrayList.add(new Currency("RUB", response.body().rates.RUB));
                                        currencyArrayList.add(new Currency("SEK", response.body().rates.SEK));
                                        currencyArrayList.add(new Currency("SGD", response.body().rates.SGD));
                                        currencyArrayList.add(new Currency("THB", response.body().rates.THB));
                                        currencyArrayList.add(new Currency("TRY", response.body().rates.TRY));
                                        currencyArrayList.add(new Currency("USD", response.body().rates.USD));
                                        currencyArrayList.add(new Currency("ZAR", response.body().rates.ZAR));
                                        currencyAdapter.updateList(currencyArrayList);
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<CurrencyRequestRestModel> call, @NonNull Throwable t) {
//                        Toast.makeText(this, getResources().getString(R.string.network_error),
//                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
