package ru.mirea.bublikov.domain.usecases;

import ru.mirea.bublikov.domain.repository.AuthCallback;
import ru.mirea.bublikov.domain.repository.UserRepository;

public class LoginUseCase {
    private final UserRepository repository;

    public LoginUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public void execute(String login, String password, AuthCallback callback) {
        repository.loginUser(login, password, callback);
    }
}