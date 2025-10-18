package ru.mirea.bublikov.domain.repository;

public interface CurrencyRepository {
    String getCurrencyRate(String currencyCode);
}