package ru.mirea.bublikov.shoppinglist.domain.repository;

public interface UserRepository {
    boolean loginUser(String login, String password);
    boolean registerUser(String login, String password);
}