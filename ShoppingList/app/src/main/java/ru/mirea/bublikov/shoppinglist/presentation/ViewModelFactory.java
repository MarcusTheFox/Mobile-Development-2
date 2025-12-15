package ru.mirea.bublikov.shoppinglist.presentation;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.bublikov.data.repository.NetworkCurrencyRepositoryImpl;
import ru.mirea.bublikov.data.repository.ShoppingListRepositoryImpl;
import ru.mirea.bublikov.domain.repository.CurrencyRepository;
import ru.mirea.bublikov.domain.repository.ShoppingListRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Context context;

    public ViewModelFactory(Context context) {
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        ShoppingListRepository repository = new ShoppingListRepositoryImpl(context);

        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(repository);
        } else if (modelClass.isAssignableFrom(ShopItemViewModel.class)) {
            return (T) new ShopItemViewModel(repository);
        } else if (modelClass.isAssignableFrom(CurrencyViewModel.class)) {
            CurrencyRepository currencyRepository = new NetworkCurrencyRepositoryImpl();
            return (T) new CurrencyViewModel(currencyRepository);
        } else if (modelClass.isAssignableFrom(ProfileViewModel.class)) {
            return (T) new ProfileViewModel(repository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
