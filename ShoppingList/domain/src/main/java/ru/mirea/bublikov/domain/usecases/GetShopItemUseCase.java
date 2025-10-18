package ru.mirea.bublikov.domain.usecases;

import ru.mirea.bublikov.domain.models.ShopItem;
import ru.mirea.bublikov.domain.repository.ShoppingListRepository;

public class GetShopItemUseCase {

    private final ShoppingListRepository repository;

    public GetShopItemUseCase(ShoppingListRepository repository) {
        this.repository = repository;
    }

    public ShopItem execute(int shopItemId) {
        return repository.getShopItem(shopItemId);
    }
}