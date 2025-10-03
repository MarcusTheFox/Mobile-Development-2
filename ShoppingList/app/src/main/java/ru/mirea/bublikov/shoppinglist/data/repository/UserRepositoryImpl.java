package ru.mirea.bublikov.shoppinglist.data.repository;

import ru.mirea.bublikov.shoppinglist.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public boolean loginUser(String login, String password) {
        return true;
    }

    @Override
    public boolean registerUser(String login, String password) {
        return true;
    }
}
