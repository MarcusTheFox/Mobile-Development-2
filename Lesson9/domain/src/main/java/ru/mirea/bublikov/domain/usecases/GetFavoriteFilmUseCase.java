package ru.mirea.bublikov.domain.usecases;

import ru.mirea.bublikov.domain.models.Movie;
import ru.mirea.bublikov.domain.repository.MovieRepository;

public class GetFavoriteFilmUseCase {
    private final MovieRepository movieRepository;

    public GetFavoriteFilmUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie execute() {
        return movieRepository.getMovie();
    }
}