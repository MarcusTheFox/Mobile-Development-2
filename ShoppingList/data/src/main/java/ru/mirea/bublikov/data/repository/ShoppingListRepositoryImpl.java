package ru.mirea.bublikov.data.repository;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import ru.mirea.bublikov.data.network.MockNetworkApi;
import ru.mirea.bublikov.data.network.NetworkApi;
import ru.mirea.bublikov.data.storage.database.AppDatabase;
import ru.mirea.bublikov.data.storage.database.ShopItemDbModel;
import ru.mirea.bublikov.data.storage.sharedprefs.ClientStorage;
import ru.mirea.bublikov.data.storage.sharedprefs.SharedPrefsClientStorage;
import ru.mirea.bublikov.domain.models.ShopItem;
import ru.mirea.bublikov.domain.repository.ShoppingListRepository;

public class ShoppingListRepositoryImpl implements ShoppingListRepository {

    private final AppDatabase database;
    private final NetworkApi networkApi;
    private final ClientStorage clientStorage;
    private final ShopListMapper mapper = new ShopListMapper();

    public ShoppingListRepositoryImpl(Context context) {
        database = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "database.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        networkApi = new MockNetworkApi();
        clientStorage = new SharedPrefsClientStorage(context);
    }

    @Override
    public void addShopItem(ShopItem shopItem) {
        database.shopListDao().addShopItem(mapper.mapEntityToDbModel(shopItem));
    }

    @Override
    public void deleteShopItem(ShopItem shopItem) {
        database.shopListDao().deleteShopItem(shopItem.getId());
    }

    @Override
    public void editShopItem(ShopItem shopItem) {
        database.shopListDao().addShopItem(mapper.mapEntityToDbModel(shopItem));
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
        ShopItemDbModel currentItemFromDb = database.shopListDao().getShopItem(shopItem.getId());
        currentItemFromDb.enabled = !currentItemFromDb.enabled;
        database.shopListDao().addShopItem(currentItemFromDb);
    }

    @Override
    public void syncWithNetwork() {
        networkApi.syncData(getShopList());
    }

    @Override
    public void saveUserEmail(String email) {
        clientStorage.saveEmail(email);
    }

    @Override
    public String getUserEmail() {
        return clientStorage.getEmail();
    }
}