package ru.mirea.bublikov.shoppinglist.presentation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.mirea.bublikov.shoppinglist.R;

public class CurrencyViewHolder extends RecyclerView.ViewHolder {
    final ImageView imageFlag;
    final TextView textName;
    final TextView textRate;

    public CurrencyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageFlag = itemView.findViewById(R.id.imageFlag);
        textName = itemView.findViewById(R.id.textCurrencyName);
        textRate = itemView.findViewById(R.id.textCurrencyRate);
    }

    public ImageView getImageFlag() {
        return imageFlag;
    }

    public TextView getTextName() {
        return textName;
    }

    public TextView getTextRate() {
        return textRate;
    }
}