package ru.mirea.bublikov.shoppinglist.data.repository;

import ru.mirea.bublikov.shoppinglist.domain.repository.CurrencyRepository;

public class CurrencyRepositoryImpl implements CurrencyRepository {
    @Override
    public String getCurrencyRate(String currencyCode) {
        return "81.9 RUB";
    }
}
