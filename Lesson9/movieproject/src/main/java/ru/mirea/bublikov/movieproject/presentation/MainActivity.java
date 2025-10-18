package ru.mirea.bublikov.movieproject.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.mirea.bublikov.movieproject.R;
import ru.mirea.bublikov.movieproject.data.repository.MovieRepositoryImpl;
import ru.mirea.bublikov.movieproject.data.storage.MovieStorage;
import ru.mirea.bublikov.movieproject.data.storage.SharedPrefMovieStorage;
import ru.mirea.bublikov.movieproject.domain.models.Movie;
import ru.mirea.bublikov.movieproject.domain.repository.MovieRepository;
import ru.mirea.bublikov.movieproject.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.bublikov.movieproject.domain.usecases.SaveMovieToFavoriteUseCase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieStorage storage = new SharedPrefMovieStorage(this);
        MovieRepository movieRepository = new MovieRepositoryImpl(storage);
        GetFavoriteFilmUseCase getFavoriteFilmUseCase = new GetFavoriteFilmUseCase(movieRepository);
        SaveMovieToFavoriteUseCase saveMovieToFavoriteUseCase = new SaveMovieToFavoriteUseCase(movieRepository);

        TextView textViewMovie = findViewById(R.id.textViewMovie);
        EditText editTextMovie = findViewById(R.id.editTextMovie);
        Button buttonSaveMovie = findViewById(R.id.buttonSaveMovie);
        Button buttonGetMovie = findViewById(R.id.buttonGetMovie);

        buttonSaveMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editTextMovie.getText().toString();
                Movie movie = new Movie(2, text);
                boolean result = saveMovieToFavoriteUseCase.execute(movie);
                textViewMovie.setText(String.format("Save result %s", result));
            }
        });

        buttonGetMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie = getFavoriteFilmUseCase.execute();
                textViewMovie.setText(String.format("Movie name: %s", movie.getName()));
            }
        });
    }
}