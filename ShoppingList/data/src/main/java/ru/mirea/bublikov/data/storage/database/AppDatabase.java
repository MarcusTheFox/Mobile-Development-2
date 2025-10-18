package ru.mirea.bublikov.data.storage.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ShopItemDbModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShopListDao shopListDao();
}