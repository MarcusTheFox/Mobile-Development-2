package ru.mirea.bublikov.data.storage;

import java.util.List;

import ru.mirea.bublikov.domain.models.ShopItem;

public interface ShoppingListStorage {
    void add(ShopItem shopItem);
    void delete(ShopItem shopItem);
    void edit(ShopItem shopItem);
    void mark(ShopItem shopItem);
    ShopItem get(int shopItemId);
    List<ShopItem> getList();
}
