package ru.mirea.bublikov.fragmentmanagerapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> selectedCountry = new MutableLiveData<>();

    public void selectItem(String item) {
        selectedCountry.setValue(item);
    }

    public LiveData<String> getSelectedItem() {
        return selectedCountry;
    }
}