package ru.mirea.bublikov.movieproject.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import ru.mirea.bublikov.movieproject.domain.models.Movie;
import ru.mirea.bublikov.movieproject.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {
    private static final String PREFERENCES_NAME = "movie_prefs";
    private static final String KEY_MOVIE_NAME = "movie_name";
    private static final String DEFAULT_MOVIE_NAME = "Фильм не сохранен";

    private final SharedPreferences sharedPreferences;

    public MovieRepositoryImpl(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean saveMovie(Movie movie) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_MOVIE_NAME, movie.getName());
        editor.apply();
        return true;
    }

    @Override
    public Movie getMovie() {
        String movieName = sharedPreferences.getString(KEY_MOVIE_NAME, DEFAULT_MOVIE_NAME);
        return new Movie(1, movieName);
    }
}
