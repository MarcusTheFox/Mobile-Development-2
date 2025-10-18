package ru.mirea.bublikov.data.repository;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import ru.mirea.bublikov.data.network.MockNetworkApi;
import ru.mirea.bublikov.data.network.NetworkApi;
import ru.mirea.bublikov.data.storage.database.AppDatabase;
import ru.mirea.bublikov.data.storage.database.ShopItemDbModel;
import ru.mirea.bublikov.domain.models.ShopItem;
import ru.mirea.bublikov.domain.repository.ShoppingListRepository;

public class ShoppingListRepositoryImpl implements ShoppingListRepository {

    private final AppDatabase database;
    private final NetworkApi networkApi;
    private final ShopListMapper mapper = new ShopListMapper();

    public ShoppingListRepositoryImpl(Context context) {
        database = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "database.db")
                .allowMainThreadQueries()
                .build();
        networkApi = new MockNetworkApi();
    }

    @Override
    public void addShopItem(ShopItem shopItem) {
        database.shopListDao().addShopItem(mapper.mapEntityToDbModel(shopItem));
        networkApi.syncData(getShopList());
    }

    @Override
    public void deleteShopItem(ShopItem shopItem) {
        database.shopListDao().deleteShopItem(shopItem.getId());
        networkApi.syncData(getShopList());
    }

    @Override
    public void editShopItem(ShopItem shopItem) {
        database.shopListDao().addShopItem(mapper.mapEntityToDbModel(shopItem));
        networkApi.syncData(getShopList());
    }

    @Override
    public ShopItem getShopItem(int shopItemId) {
        ShopItemDbModel dbModel = database.shopListDao().getShopItem(shopItemId);
        return mapper.mapDbModelToEntity(dbModel);
    }

    @Override
    public List<ShopItem> getShopList() {
        List<ShopItemDbModel> dbList = database.shopListDao().getShopList();
        return mapper.mapListDbModelToListEntity(dbList);
    }

    @Override
    public void markShopItem(ShopItem shopItem) {
        shopItem.setEnabled(!shopItem.isEnabled());
        editShopItem(shopItem);
    }
}