package ru.mirea.bublikov.shoppinglist.presentation;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.mirea.bublikov.domain.models.ShopItem;
import ru.mirea.bublikov.domain.repository.ShoppingListRepository;
import ru.mirea.bublikov.domain.usecases.GetShoppingListUseCase;

public class MainViewModel extends ViewModel {

    private final ShoppingListRepository repository;

    private final MutableLiveData<List<ShopItem>> shopList = new MutableLiveData<>();
    public LiveData<List<ShopItem>> getShopList() {
        return shopList;
    }

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MainViewModel(ShoppingListRepository repository) {
        this.repository = repository;
    }

    public void initialLoad() {
        isLoading.setValue(true);
        new Thread(() -> {
            repository.syncWithNetwork();
            refreshListFromDb();
            new Handler(Looper.getMainLooper()).post(() -> isLoading.setValue(false));
        }).start();
    }

    public void loadShopList() {
        new Thread(() -> {
            refreshListFromDb();
            repository.syncWithNetwork();
        }).start();
    }

    public void deleteShopItem(ShopItem shopItem) {
        new Thread(() -> {
            repository.deleteShopItem(shopItem);
            refreshListFromDb();
            repository.syncWithNetwork();
        }).start();
    }

    public void markShopItem(ShopItem shopItem) {
        new Thread(() -> {
            repository.markShopItem(shopItem);
            refreshListFromDb();
            repository.syncWithNetwork();
        }).start();
    }

    private void refreshListFromDb() {
        List<ShopItem> currentList = new GetShoppingListUseCase(repository).execute();
        shopList.postValue(currentList);
    }
}