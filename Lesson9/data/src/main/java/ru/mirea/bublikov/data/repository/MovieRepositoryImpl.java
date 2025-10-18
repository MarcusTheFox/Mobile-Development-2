package ru.mirea.bublikov.data.repository;

import ru.mirea.bublikov.data.storage.MovieStorage;
import ru.mirea.bublikov.domain.models.Movie;
import ru.mirea.bublikov.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {
    private final MovieStorage movieStorage;

    public MovieRepositoryImpl(MovieStorage movieStorage) {
        this.movieStorage = movieStorage;
    }

    @Override
    public boolean saveMovie(Movie movie) {
        return movieStorage.save(movie);
    }

    @Override
    public Movie getMovie() {
        return movieStorage.get();
    }
}
