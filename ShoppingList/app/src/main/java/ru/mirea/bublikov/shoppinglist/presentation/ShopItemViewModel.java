package ru.mirea.bublikov.shoppinglist.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.bublikov.domain.models.ShopItem;
import ru.mirea.bublikov.domain.repository.ShoppingListRepository;
import ru.mirea.bublikov.domain.usecases.AddItemToListUseCase;
import ru.mirea.bublikov.domain.usecases.EditItemUseCase;
import ru.mirea.bublikov.domain.usecases.GetShopItemUseCase;

public class ShopItemViewModel extends ViewModel {
    private final ShoppingListRepository repository;

    private MutableLiveData<ShopItem> _shopItem = new MutableLiveData<>();
    public LiveData<ShopItem> shopItem = _shopItem;

    private MutableLiveData<Boolean> _shouldCloseScreen = new MutableLiveData<>();
    public LiveData<Boolean> shouldCloseScreen = _shouldCloseScreen;

    public ShopItemViewModel(ShoppingListRepository repository) {
        this.repository = repository;
    }

    public void getShopItem(int itemId) {
        ShopItem item = new GetShopItemUseCase(repository).execute(itemId);
        _shopItem.setValue(item);
    }

    public void saveShopItem(String inputName, String inputCount, String inputPrice) {
        int count = Integer.parseInt(inputCount);
        double price = Double.parseDouble(inputPrice);

        ShopItem currentItem = _shopItem.getValue();

        new Thread(() -> {
            if (currentItem != null) {
                currentItem.setName(inputName);
                currentItem.setCount(count);
                currentItem.setPrice(price);
                new EditItemUseCase(repository).execute(currentItem);
            } else {
                new AddItemToListUseCase(repository).execute(new ShopItem(inputName, count, price, false));
            }
            _shouldCloseScreen.postValue(true);
        }).start();
    }
}