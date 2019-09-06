package com.revolut.testapp;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Currency  {
    private String nameCurrency;
    private Float currencyValue;

    public Currency(String nameCurrency, Float currencyValue) {
        this.nameCurrency = nameCurrency;
        this.currencyValue= currencyValue;
    }

    public String getNameCurrency() {
        return nameCurrency;
    }

    public Float getCurrencyValue() {
        return currencyValue;
    }

    public void setNameCurrency(String nameCurrency) {
        this.nameCurrency = nameCurrency;
    }

    public void setCurrencyValue(Float currencyValue) {
        this.currencyValue = currencyValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Currency currency = (Currency) obj;
        if (!nameCurrency.equalsIgnoreCase(currency.nameCurrency)) return false;
        return Objects.equals(currencyValue, currency.currencyValue);
    }


}
