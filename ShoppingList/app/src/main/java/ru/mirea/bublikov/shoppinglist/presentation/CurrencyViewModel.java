package ru.mirea.bublikov.shoppinglist.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.mirea.bublikov.domain.models.Currency;
import ru.mirea.bublikov.domain.repository.CurrencyRepository;
import ru.mirea.bublikov.domain.usecases.GetCurrencyRatesUseCase;

public class CurrencyViewModel extends ViewModel {
    private final GetCurrencyRatesUseCase getCurrencyRatesUseCase;
    private final MutableLiveData<List<Currency>> currencyList = new MutableLiveData<>();

    public LiveData<List<Currency>> getCurrencyList() {
        return currencyList;
    }

    public CurrencyViewModel(CurrencyRepository repository) {
        this.getCurrencyRatesUseCase = new GetCurrencyRatesUseCase(repository);
        loadCurrencies();
    }

    private void loadCurrencies() {
        new Thread(() -> {
            List<Currency> rates = getCurrencyRatesUseCase.execute();
            currencyList.postValue(rates);
        }).start();
    }
}
