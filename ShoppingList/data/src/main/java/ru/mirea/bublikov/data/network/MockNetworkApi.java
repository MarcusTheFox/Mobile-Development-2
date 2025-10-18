package ru.mirea.bublikov.data.network;

import java.util.List;

import ru.mirea.bublikov.domain.models.ShopItem;

public class MockNetworkApi implements NetworkApi {
    @Override
    public boolean syncData(List<ShopItem> shopList) {
        try {
            Thread.sleep(2000);
            System.out.println("Синхронизировано " + shopList.size() + " элементов с сервером.");
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}