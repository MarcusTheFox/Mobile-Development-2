package ru.mirea.bublikov.data.storage.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsClientStorage implements ClientStorage {

    private static final String PREFS_NAME = "client_info";
    private static final String KEY_EMAIL = "user_email";
    private final SharedPreferences sharedPreferences;

    public SharedPrefsClientStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void saveEmail(String email) {
        sharedPreferences.edit().putString(KEY_EMAIL, email).apply();
    }

    @Override
    public String getEmail() {
        return sharedPreferences.getString(KEY_EMAIL, "unknown@email.com");
    }
}