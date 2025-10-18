package ru.mirea.bublikov.domain.usecases;

import ru.mirea.bublikov.domain.repository.CurrencyRepository;

public class GetCurrencyRatesUseCase {
    private final CurrencyRepository repository;

    public GetCurrencyRatesUseCase(CurrencyRepository repository) {
        this.repository = repository;
    }

    public String execute(String currencyCode) {
        return repository.getCurrencyRate(currencyCode);
    }
}