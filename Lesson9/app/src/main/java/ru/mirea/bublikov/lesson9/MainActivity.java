package ru.mirea.bublikov.lesson9;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.bublikov.data.repository.MovieRepositoryImpl;
import ru.mirea.bublikov.data.storage.SharedPrefMovieStorage;
import ru.mirea.bublikov.data.storage.MovieStorage;
import ru.mirea.bublikov.domain.models.Movie;
import ru.mirea.bublikov.domain.repository.MovieRepository;
import ru.mirea.bublikov.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.bublikov.domain.usecases.SaveMovieToFavoriteUseCase;

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