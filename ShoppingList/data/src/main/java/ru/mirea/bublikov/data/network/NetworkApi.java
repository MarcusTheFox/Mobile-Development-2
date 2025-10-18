package ru.mirea.bublikov.data.network;

import java.util.List;

import ru.mirea.bublikov.domain.models.ShopItem;

public interface NetworkApi {
    boolean syncData(List<ShopItem> shopList);
}