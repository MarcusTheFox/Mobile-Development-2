package ru.mirea.bublikov.shoppinglist.domain.usecases;

import java.util.List;

import ru.mirea.bublikov.shoppinglist.domain.models.ShopItem;
import ru.mirea.bublikov.shoppinglist.domain.repository.ShoppingListRepository;

public class GetShoppingListUseCase {
    private final ShoppingListRepository repository;

    public GetShoppingListUseCase(ShoppingListRepository repository) {
        this.repository = repository;
    }

    public List<ShopItem> execute() {
        return repository.getShopList();
    }
}