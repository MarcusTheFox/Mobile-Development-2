package ru.mirea.bublikov.lesson9.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.bublikov.domain.models.Movie;
import ru.mirea.bublikov.lesson9.R;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewModelFactory factory = new ViewModelFactory(this);
        mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        TextView textViewMovie = findViewById(R.id.textViewMovie);
        EditText editTextMovie = findViewById(R.id.editTextMovie);
        Button buttonSaveMovie = findViewById(R.id.buttonSaveMovie);
        Button buttonGetMovie = findViewById(R.id.buttonGetMovie);

        mainViewModel.getFavoriteMovie().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textViewMovie.setText(s);
            }
        });

        buttonSaveMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editTextMovie.getText().toString();
                Movie movie = new Movie(2, text);
                mainViewModel.setText(movie);
            }
        });

        buttonGetMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.getText();
            }
        });
    }
}