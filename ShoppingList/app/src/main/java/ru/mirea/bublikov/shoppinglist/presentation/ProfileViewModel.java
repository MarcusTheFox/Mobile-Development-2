package ru.mirea.bublikov.shoppinglist.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.bublikov.domain.repository.ShoppingListRepository;

public class ProfileViewModel extends ViewModel {
    private final ShoppingListRepository repository;
    private final MutableLiveData<String> _email = new MutableLiveData<>();
    public LiveData<String> email = _email;

    public ProfileViewModel(ShoppingListRepository repository) {
        this.repository = repository;
        loadProfileData();
    }

    private void loadProfileData() {
        String savedEmail = repository.getUserEmail();
        _email.setValue(savedEmail);
    }
}