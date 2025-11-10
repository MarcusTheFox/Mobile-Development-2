package ru.mirea.bublikov.data.repository;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.bublikov.domain.models.Currency;
import ru.mirea.bublikov.domain.models.CurrencyCode;
import ru.mirea.bublikov.domain.repository.CurrencyRepository;

public class CurrencyRepositoryImpl implements CurrencyRepository {
    @Override
    public List<Currency> getCurrencyRates() {
        List<Currency> rates = new ArrayList<>();
        rates.add(new Currency(CurrencyCode.RUB, 1.0));
        rates.add(new Currency(CurrencyCode.USD, 95.50));
        rates.add(new Currency(CurrencyCode.EUR, 102.30));
        rates.add(new Currency(CurrencyCode.CNY, 13.10));
        return rates;
    }
}
