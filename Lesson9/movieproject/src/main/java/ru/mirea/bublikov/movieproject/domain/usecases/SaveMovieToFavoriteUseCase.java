package ru.mirea.bublikov.movieproject.domain.usecases;

import ru.mirea.bublikov.movieproject.domain.models.Movie;
import ru.mirea.bublikov.movieproject.domain.repository.MovieRepository;

public class SaveMovieToFavoriteUseCase {
    private final MovieRepository movieRepository;

    public SaveMovieToFavoriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public boolean execute(Movie movie) {
        return movieRepository.saveMovie(movie);
    }
}