package ru.mirea.bublikov.data.storage.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shop_items")
public class ShopItemDbModel {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public int count;
    public boolean enabled;
}
