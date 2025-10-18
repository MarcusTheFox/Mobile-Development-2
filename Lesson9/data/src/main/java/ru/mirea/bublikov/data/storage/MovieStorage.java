package ru.mirea.bublikov.data.storage;

import ru.mirea.bublikov.domain.models.Movie;

public interface MovieStorage {
    boolean save(Movie movie);
    Movie get();
}
