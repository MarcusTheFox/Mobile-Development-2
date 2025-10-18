package ru.mirea.bublikov.data.repository;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ru.mirea.bublikov.data.storage.sharedprefs.ClientStorage;
import ru.mirea.bublikov.data.storage.sharedprefs.SharedPrefsClientStorage;
import ru.mirea.bublikov.domain.repository.AuthCallback;
import ru.mirea.bublikov.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private final FirebaseAuth mAuth;
    private final ClientStorage clientStorage;

    public UserRepositoryImpl(Context context) {
        mAuth = FirebaseAuth.getInstance();
        clientStorage = new SharedPrefsClientStorage(context);
    }

    @Override
    public void loginUser(String login, String password, AuthCallback callback) {
        mAuth.signInWithEmailAndPassword(login, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            clientStorage.saveEmail(login);
                            callback.onSuccess();
                        } else {
                            callback.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }

    @Override
    public void registerUser(String login, String password, AuthCallback callback) {
        mAuth.createUserWithEmailAndPassword(login, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            clientStorage.saveEmail(login);
                            callback.onSuccess();
                        } else {
                            callback.onFailure(task.getException().getMessage());
                        }
                    }
                });
    }
}
