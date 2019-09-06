package com.revolut.testapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;


import java.util.List;

public class CurrentsDiffUtilCallback extends DiffUtil.Callback {

    private final List<Currency> oldList;
    private final List<Currency> newList;

    public CurrentsDiffUtilCallback(List<Currency> oldList, List<Currency> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Currency oldCurrency = oldList.get(oldItemPosition);
        Currency newCurrency = newList.get(newItemPosition);
        return oldCurrency.getNameCurrency().equals(newCurrency.getNameCurrency()) ;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Currency oldCurrency = oldList.get(oldItemPosition);
        Currency newCurrency = newList.get(newItemPosition);
        return oldCurrency.getNameCurrency().equals(newCurrency.getNameCurrency()) &&
                    oldCurrency.getCurrencyValue().equals(newCurrency.getCurrencyValue())  ;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
          //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
