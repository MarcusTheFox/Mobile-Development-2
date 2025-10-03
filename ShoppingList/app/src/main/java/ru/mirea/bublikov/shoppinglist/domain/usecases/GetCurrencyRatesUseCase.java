package ru.mirea.bublikov.shoppinglist.domain.usecases;

import ru.mirea.bublikov.shoppinglist.domain.repository.CurrencyRepository;

public class GetCurrencyRatesUseCase {
    private final CurrencyRepository repository;

    public GetCurrencyRatesUseCase(CurrencyRepository repository) {
        this.repository = repository;
    }

    public String execute(String currencyCode) {
        return repository.getCurrencyRate(currencyCode);
    }
}