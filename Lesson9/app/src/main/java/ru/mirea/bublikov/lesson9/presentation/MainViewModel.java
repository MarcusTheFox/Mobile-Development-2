package ru.mirea.bublikov.lesson9.presentation;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.bublikov.domain.models.Movie;
import ru.mirea.bublikov.domain.repository.MovieRepository;
import ru.mirea.bublikov.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.bublikov.domain.usecases.SaveMovieToFavoriteUseCase;

public class MainViewModel extends ViewModel {
    private final MovieRepository movieRepository;
    private final MutableLiveData<String> favoriteMovie = new MutableLiveData<>();

    public MutableLiveData<String> getFavoriteMovie() {
        return favoriteMovie;
    }

    public MainViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        Log.d("MainViewModel", "MainViewModel created");
    }

    public void setText(Movie movie) {
        boolean result = new SaveMovieToFavoriteUseCase(movieRepository).execute(movie);
        favoriteMovie.setValue(Boolean.toString(result));
    }

    public void getText() {
        Movie movie = new GetFavoriteFilmUseCase(movieRepository).execute();
        favoriteMovie.setValue(String.format("My favorite movie is %s", movie.getName()));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("MainViewModel", "MainViewModel cleared");
    }
}
