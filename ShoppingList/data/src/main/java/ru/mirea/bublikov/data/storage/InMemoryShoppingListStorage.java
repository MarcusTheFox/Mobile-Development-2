package ru.mirea.bublikov.data.storage;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.bublikov.domain.models.CurrencyCode;
import ru.mirea.bublikov.domain.models.ShopItem;

public class InMemoryShoppingListStorage implements ShoppingListStorage {
    private final List<ShopItem> shopList = new ArrayList<>();
    private int autoIncrementId = 0;

    public InMemoryShoppingListStorage() {
        add(new ShopItem("Хлеб", 1, 150, CurrencyCode.RUB, true));
        add(new ShopItem("Молоко", 2, 100, CurrencyCode.RUB, false));
        add(new ShopItem("Сыр", 1, 500, CurrencyCode.RUB, false));
    }

    @Override
    public void add(ShopItem shopItem) {
        if (shopItem.getId() == ShopItem.UNDEFINED_ID) {
            shopItem.setId(autoIncrementId++);
        }
        shopList.add(shopItem);
    }

    @Override
    public void delete(ShopItem shopItem) {
        shopList.remove(shopItem);
    }

    @Override
    public void edit(ShopItem shopItem) {
        ShopItem oldItem = get(shopItem.getId());
        shopList.remove(oldItem);
        shopList.add(shopItem);
    }

    @Override
    public void mark(ShopItem shopItem) {
        ShopItem itemInList = get(shopItem.getId());
        if (itemInList != null) {
            itemInList.setEnabled(!itemInList.isEnabled());
        }
    }

    @Override
    public ShopItem get(int shopItemId) {
        for (ShopItem item : shopList) {
            if (item.getId() == shopItemId) {
                return item;
            }
        }
        return null;
    }

    @Override
    public List<ShopItem> getList() {
        return new ArrayList<>(shopList);
    }
}
