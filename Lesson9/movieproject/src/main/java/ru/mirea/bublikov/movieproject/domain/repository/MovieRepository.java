package ru.mirea.bublikov.movieproject.domain.repository;

import ru.mirea.bublikov.movieproject.domain.models.Movie;

public interface MovieRepository {
    boolean saveMovie(Movie movie);
    Movie getMovie();
}