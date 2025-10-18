package ru.mirea.bublikov.data.storage.sharedprefs;

public interface ClientStorage {
    void saveEmail(String email);
    String getEmail();
}