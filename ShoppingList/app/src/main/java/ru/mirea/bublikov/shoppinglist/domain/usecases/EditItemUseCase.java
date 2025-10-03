package ru.mirea.bublikov.shoppinglist.domain.usecases;

import ru.mirea.bublikov.shoppinglist.domain.models.ShopItem;
import ru.mirea.bublikov.shoppinglist.domain.repository.ShoppingListRepository;

public class EditItemUseCase {
    private final ShoppingListRepository repository;

    public EditItemUseCase(ShoppingListRepository repository) {
        this.repository = repository;
    }

    public void execute(ShopItem shopItem) {
        repository.editShopItem(shopItem);
    }
}
