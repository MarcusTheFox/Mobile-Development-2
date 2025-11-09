package ru.mirea.bublikov.recyclerviewapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private final List<HistoricalEvent> events;
    private Context context;

    public EventAdapter(List<HistoricalEvent> events) {
        this.events = events;
    }

    @Override
    public EventViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        HistoricalEvent event = events.get(position);
        int iconResourceId = context.getResources().getIdentifier(
                event.getIconName(),
                "drawable",
                context.getPackageName()
        );

        holder.getTextEventName().setText(event.getName());
        holder.getTextEventDescription().setText(event.getDescription());
        holder.getImageEventIcon().setImageResource(iconResourceId);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}