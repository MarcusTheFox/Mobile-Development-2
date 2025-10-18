package ru.mirea.bublikov.data.repository;

import ru.mirea.bublikov.domain.repository.CurrencyRepository;

public class CurrencyRepositoryImpl implements CurrencyRepository {
    @Override
    public String getCurrencyRate(String currencyCode) {
        return "81.9 RUB";
    }
}
