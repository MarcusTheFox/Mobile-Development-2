package ru.mirea.bublikov.domain.repository;

import ru.mirea.bublikov.domain.models.Movie;

public interface MovieRepository {
    boolean saveMovie(Movie movie);
    Movie getMovie();
}