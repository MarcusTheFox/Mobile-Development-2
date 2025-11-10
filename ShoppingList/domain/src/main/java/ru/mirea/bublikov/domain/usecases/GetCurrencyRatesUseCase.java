package ru.mirea.bublikov.domain.usecases;

import java.util.List;

import ru.mirea.bublikov.domain.models.Currency;
import ru.mirea.bublikov.domain.repository.CurrencyRepository;

public class GetCurrencyRatesUseCase {
    private final CurrencyRepository repository;

    public GetCurrencyRatesUseCase(CurrencyRepository repository) {
        this.repository = repository;
    }

    public List<Currency> execute() {
        return repository.getCurrencyRates();
    }
}