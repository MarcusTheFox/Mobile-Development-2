package ru.mirea.bublikov.shoppinglist.domain.repository;

public interface CurrencyRepository {
    String getCurrencyRate(String currencyCode);
}