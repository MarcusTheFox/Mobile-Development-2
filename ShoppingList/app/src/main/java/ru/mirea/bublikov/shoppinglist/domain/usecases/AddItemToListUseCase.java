package ru.mirea.bublikov.shoppinglist.domain.usecases;

import ru.mirea.bublikov.shoppinglist.domain.models.ShopItem;
import ru.mirea.bublikov.shoppinglist.domain.repository.ShoppingListRepository;

public class AddItemToListUseCase {
    private final ShoppingListRepository repository;

    public AddItemToListUseCase(ShoppingListRepository repository) {
        this.repository = repository;
    }

    public void execute(ShopItem shopItem) {
        repository.addShopItem(shopItem);
    }
}