package ru.mirea.bublikov.shoppinglist.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.bublikov.domain.models.Currency;
import ru.mirea.bublikov.shoppinglist.R;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyViewHolder> {
    private List<Currency> currencies = new ArrayList<>();
    private Context context;

    public void setItems(List<Currency> items) {
        this.currencies = items;
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_item, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        Currency currency = currencies.get(position);
        int flagResId = context.getResources().getIdentifier(
                currency.getFlagName(),
                "drawable",
                context.getPackageName()
        );

        holder.getTextName().setText(String.format("%s (%s)", currency.getName(), currency.getCode()));
        holder.getTextRate().setText(String.format("%.2f RUB", currency.getRate()));
        holder.getImageFlag().setImageResource(flagResId);
    }

    @Override public int getItemCount() { return currencies.size(); }
}