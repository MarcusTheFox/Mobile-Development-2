package ru.mirea.bublikov.domain.repository;

import java.util.List;

import ru.mirea.bublikov.domain.models.Currency;

public interface CurrencyRepository {
    List<Currency> getCurrencyRates();
}