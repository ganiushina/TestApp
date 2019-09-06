package com.revolut.testapp.rest.entities;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class CurrencyRequestRestModel {
    @SerializedName("base") public String nameCurrency;
    @SerializedName("date") public Date dateUpdate;
    @SerializedName("rates") public RatesRestModel rates;
}
