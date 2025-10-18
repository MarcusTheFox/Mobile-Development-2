package ru.mirea.bublikov.shoppinglist.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import ru.mirea.bublikov.data.repository.UserRepositoryImpl;
import ru.mirea.bublikov.domain.repository.AuthCallback;
import ru.mirea.bublikov.domain.repository.UserRepository;
import ru.mirea.bublikov.domain.usecases.LoginUseCase;
import ru.mirea.bublikov.domain.usecases.RegisterUseCase;
import ru.mirea.bublikov.shoppinglist.R;

public class SignInActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignIn;
    private Button buttonRegister;

    private LoginUseCase loginUseCase;
    private RegisterUseCase registerUseCase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonRegister = findViewById(R.id.buttonRegister);

        UserRepository repository = new UserRepositoryImpl(this);
        loginUseCase = new LoginUseCase(repository);
        registerUseCase = new RegisterUseCase(repository);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            navigateToMainScreen();
            return;
        }

        buttonSignIn.setOnClickListener(v -> {
            if (!validateForm()) return;

            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            loginUseCase.execute(email, password, new AuthCallback() {
                @Override
                public void onSuccess() {
                    Toast.makeText(SignInActivity.this, "Вход успешен!", Toast.LENGTH_SHORT).show();
                    navigateToMainScreen();
                }

                @Override
                public void onFailure(String errorMessage) {
                    Toast.makeText(SignInActivity.this, "Ошибка входа: " + errorMessage, Toast.LENGTH_LONG).show();
                }
            });
        });

        buttonRegister.setOnClickListener(v -> {
            if (!validateForm()) return;

            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            registerUseCase.execute(email, password, new AuthCallback() {
                @Override
                public void onSuccess() {
                    Toast.makeText(SignInActivity.this, "Регистрация успешна!", Toast.LENGTH_SHORT).show();
                    navigateToMainScreen();

                }

                @Override
                public void onFailure(String errorMessage) {
                    Toast.makeText(SignInActivity.this, "Ошибка регистрации: " + errorMessage, Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    private void navigateToMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validateForm() {
        if (TextUtils.isEmpty(editTextEmail.getText().toString())) {
            Toast.makeText(this, "Введите email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}