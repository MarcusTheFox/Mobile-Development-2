package ru.mirea.bublikov.shoppinglist.data.repository;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.bublikov.shoppinglist.domain.models.ShopItem;
import ru.mirea.bublikov.shoppinglist.domain.repository.ShoppingListRepository;

public class ShoppingListRepositoryImpl implements ShoppingListRepository {
    private final List<ShopItem> shopList = new ArrayList<>();
    private int autoIncrementId = 0;

    public ShoppingListRepositoryImpl() {
        addShopItem(new ShopItem("Хлеб", 1, true));
        addShopItem(new ShopItem("Молоко", 2, false));
        addShopItem(new ShopItem("Сыр", 1, false));
    }

    @Override
    public void addShopItem(ShopItem shopItem) {
        if (shopItem.getId() == ShopItem.UNDEFINED_ID) {
            shopItem.setId(autoIncrementId++);
        }
        shopList.add(shopItem);
    }

    @Override
    public void deleteShopItem(ShopItem shopItem) {
        shopList.remove(shopItem);
    }

    @Override
    public void editShopItem(ShopItem shopItem) {
        ShopItem oldItem = getShopItem(shopItem.getId());
        shopList.remove(oldItem);
        shopList.add(shopItem);
    }

    @Override
    public void markShopItem(ShopItem shopItem) {
        ShopItem itemInList = getShopItem(shopItem.getId());
        if (itemInList != null) {
            itemInList.setEnabled(!itemInList.isEnabled());
        }
    }

    @Override
    public ShopItem getShopItem(int shopItemId) {
        for (ShopItem item : shopList) {
            if (item.getId() == shopItemId) {
                return item;
            }
        }
        return null;
    }

    @Override
    public List<ShopItem> getShopList() {
        return new ArrayList<>(shopList);
    }
}