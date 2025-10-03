package ru.mirea.bublikov.shoppinglist.domain.usecases;

import ru.mirea.bublikov.shoppinglist.domain.repository.UserRepository;

public class RegisterUseCase {
    private final UserRepository repository;

    public RegisterUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String login, String password) {
        return repository.registerUser(login, password);
    }
}