package ru.mirea.bublikov.movieproject.data.storage;

import ru.mirea.bublikov.movieproject.domain.models.Movie;

public interface MovieStorage {
    boolean save(Movie movie);
    Movie get();
}
