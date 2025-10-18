package ru.mirea.bublikov.domain.usecases;

import java.util.List;

import ru.mirea.bublikov.domain.models.ShopItem;
import ru.mirea.bublikov.domain.repository.ShoppingListRepository;

public class GetShoppingListUseCase {
    private final ShoppingListRepository repository;

    public GetShoppingListUseCase(ShoppingListRepository repository) {
        this.repository = repository;
    }

    public List<ShopItem> execute() {
        return repository.getShopList();
    }
}