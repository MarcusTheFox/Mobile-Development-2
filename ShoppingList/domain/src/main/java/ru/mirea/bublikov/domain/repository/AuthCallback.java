package ru.mirea.bublikov.domain.repository;

public interface AuthCallback {
    void onSuccess();
    void onFailure(String errorMessage);
}
