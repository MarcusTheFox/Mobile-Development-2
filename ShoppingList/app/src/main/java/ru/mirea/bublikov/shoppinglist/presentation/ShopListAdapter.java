package ru.mirea.bublikov.shoppinglist.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import ru.mirea.bublikov.domain.models.ShopItem;
import ru.mirea.bublikov.shoppinglist.R;

public class ShopListAdapter extends ListAdapter<ShopItem, ShopListAdapter.ShopItemViewHolder> {
    private OnShopItemClickListener onShopItemClickListener;
    private OnDeleteItemClickListener onDeleteItemClickListener;
    private OnMarkItemClickListener onMarkItemClickListener;

    protected ShopListAdapter() {
        super(new ShopItemDiffCallback());
    }

    public void setOnShopItemClickListener(OnShopItemClickListener listener) {
        this.onShopItemClickListener = listener;
    }

    public void setOnDeleteItemClickListener(OnDeleteItemClickListener listener) {
        this.onDeleteItemClickListener = listener;
    }

    public void setOnMarkItemClickListener(OnMarkItemClickListener listener) {
        this.onMarkItemClickListener = listener;
    }

    @NonNull
    @Override
    public ShopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);
        return new ShopItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopItemViewHolder holder, int position) {
        ShopItem shopItem = getItem(position);

        holder.textViewName.setText(shopItem.getName());
        holder.textViewCount.setText(String.format("Кол-во: %d", shopItem.getCount()));
        holder.textViewPrice.setText(String.format("%.2f руб./шт.", shopItem.getPrice()));

        holder.checkBoxEnabled.setOnCheckedChangeListener(null);
        holder.checkBoxEnabled.setChecked(shopItem.isEnabled());

        holder.itemView.setOnClickListener(v -> {
            if (onShopItemClickListener != null) {
                onShopItemClickListener.onShopItemClick(shopItem);
            }
        });

        holder.checkBoxEnabled.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (onMarkItemClickListener != null) {
                onMarkItemClickListener.onMarkItemClick(shopItem);
            }
        });

        holder.buttonDelete.setOnClickListener(v -> {
            if (onDeleteItemClickListener != null) {
                onDeleteItemClickListener.onDeleteItemClick(shopItem);
            }
        });

        int backgroundColorResId = shopItem.isEnabled()
                ? android.R.color.holo_green_light
                : android.R.color.white;
        holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), backgroundColorResId));
    }

    public static class ShopItemViewHolder extends RecyclerView.ViewHolder {
        final TextView textViewName;
        final TextView textViewCount;
        final TextView textViewPrice;
        final ImageButton buttonDelete;
        final CheckBox checkBoxEnabled;

        public ShopItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewCount = itemView.findViewById(R.id.textViewCount);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            checkBoxEnabled = itemView.findViewById(R.id.checkBoxEnabled);
        }
    }

    private static class ShopItemDiffCallback extends DiffUtil.ItemCallback<ShopItem> {
        @Override
        public boolean areItemsTheSame(@NonNull ShopItem oldItem, @NonNull ShopItem newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ShopItem oldItem, @NonNull ShopItem newItem) {
            return oldItem.equals(newItem);
        }
    }

    public interface OnShopItemClickListener {
        void onShopItemClick(ShopItem shopItem);
    }

    public interface OnDeleteItemClickListener {
        void onDeleteItemClick(ShopItem shopItem);
    }

    public interface OnMarkItemClickListener {
        void onMarkItemClick(ShopItem shopItem);
    }
}
