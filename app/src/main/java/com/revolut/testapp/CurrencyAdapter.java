package com.revolut.testapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>  {

    private ArrayList<Currency> currencies;
    private AdapterView.OnItemClickListener itemClickListener;

    private int selectedPos = 0;

    CurrencyAdapter(ArrayList<Currency> currencies){
        this.currencies = currencies;
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCurrency;
        TextView textViewValue;
        CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCurrency = itemView.findViewById(R.id.textViewCurrency);
            textViewValue = itemView.findViewById(R.id.editTextCount);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Currency clickedDataItem = currencies.get(pos);
                        Currency firstDataItem = currencies.get(0);
                        currencies.remove(pos);
                        currencies.add(0, clickedDataItem);
                        currencies.add(pos, firstDataItem);
                        selectedPos= pos;
                        notifyItemMoved(pos, 0);
                        Toast.makeText(v.getContext(), clickedDataItem.getNameCurrency(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    public void updateList(ArrayList<Currency> newList) {
        final CurrentsDiffUtilCallback diffCallback = new CurrentsDiffUtilCallback(currencies, newList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        currencies.clear();
        currencies.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }


    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CurrencyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        holder.itemView.setSelected(selectedPos == position);
        holder.textViewCurrency.setText(currencies.get(position).getNameCurrency());
        holder.textViewValue.setText(Float.toString(currencies.get(position).getCurrencyValue()));
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            Bundle bundle = (Bundle) payloads.get(0);
            if (bundle.size() != 0) {
                String name = bundle.getString("nameCurrency");
                String value = bundle.getString("currencyValue");
                if (name != null) {
                    holder.textViewCurrency.setText(name);
                }
                if (value != null) {
                    holder.textViewValue.setText(value);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }


}
