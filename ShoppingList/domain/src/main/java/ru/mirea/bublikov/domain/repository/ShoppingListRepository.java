package ru.mirea.bublikov.domain.repository;

import java.util.List;

import ru.mirea.bublikov.domain.models.ShopItem;

public interface ShoppingListRepository {
    void addShopItem(ShopItem shopItem);
    void deleteShopItem(ShopItem shopItem);
    void editShopItem(ShopItem shopItem);
    void markShopItem(ShopItem shopItem);
    ShopItem getShopItem(int shopItemId);
    List<ShopItem> getShopList();
    void syncWithNetwork();
    void saveUserEmail(String email);
    String getUserEmail();
}