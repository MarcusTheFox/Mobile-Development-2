package ru.mirea.bublikov.movieproject.domain.usecases;

import ru.mirea.bublikov.movieproject.domain.models.Movie;
import ru.mirea.bublikov.movieproject.domain.repository.MovieRepository;

public class GetFavoriteFilmUseCase {
    private final MovieRepository movieRepository;

    public GetFavoriteFilmUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie execute() {
        return movieRepository.getMovie();
    }
}