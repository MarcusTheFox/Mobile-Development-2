package ru.mirea.bublikov.recyclerviewapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder {
    final ImageView imageEventIcon;
    final TextView textEventName;
    final TextView textEventDescription;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        imageEventIcon = itemView.findViewById(R.id.imageEventIcon);
        textEventName = itemView.findViewById(R.id.textEventName);
        textEventDescription = itemView.findViewById(R.id.textEventDescription);
    }

    public ImageView getImageEventIcon() {
        return imageEventIcon;
    }

    public TextView getTextEventName() {
        return textEventName;
    }

    public TextView getTextEventDescription() {
        return textEventDescription;
    }
}