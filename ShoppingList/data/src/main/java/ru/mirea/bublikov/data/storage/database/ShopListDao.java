package ru.mirea.bublikov.data.storage.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShopListDao {
    @Query("SELECT * FROM shop_items")
    List<ShopItemDbModel> getShopList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addShopItem(ShopItemDbModel shopItemDbModel);

    @Query("DELETE FROM shop_items WHERE id=:shopItemId")
    void deleteShopItem(int shopItemId);

    @Query("SELECT * FROM shop_items WHERE id=:shopItemId LIMIT 1")
    ShopItemDbModel getShopItem(int shopItemId);
}