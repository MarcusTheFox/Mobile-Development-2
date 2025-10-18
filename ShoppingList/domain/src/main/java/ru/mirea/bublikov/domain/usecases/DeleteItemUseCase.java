package ru.mirea.bublikov.domain.usecases;

import ru.mirea.bublikov.domain.models.ShopItem;
import ru.mirea.bublikov.domain.repository.ShoppingListRepository;

public class DeleteItemUseCase {
    private final ShoppingListRepository repository;

    public DeleteItemUseCase(ShoppingListRepository repository) {
        this.repository = repository;
    }

    public void execute(ShopItem shopItem) {
        repository.deleteShopItem(shopItem);
    }
}
