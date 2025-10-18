package ru.mirea.bublikov.domain.repository;

public interface UserRepository {
    void loginUser(String login, String password, AuthCallback callback);
    void registerUser(String login, String password, AuthCallback callback);
}