package ru.mirea.bublikov.domain.usecases;

import ru.mirea.bublikov.domain.repository.AuthCallback;
import ru.mirea.bublikov.domain.repository.UserRepository;

public class RegisterUseCase {
    private final UserRepository repository;

    public RegisterUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public void execute(String login, String password, AuthCallback callback) {
        repository.registerUser(login, password, callback);
    }
}