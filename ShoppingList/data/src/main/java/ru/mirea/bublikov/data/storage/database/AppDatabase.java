package ru.mirea.bublikov.data.storage.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {ShopItemDbModel.class}, version = 3, exportSchema = false)
@TypeConverters({CurrencyTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShopListDao shopListDao();
}