package ru.mirea.bublikov.resultapifragmentapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().setFragmentResultListener(
                "requestKey",
                this,
                (requestKey, bundle) -> {
                    String result = bundle.getString("key");
                    Log.d(MainActivity.class.getSimpleName(), "I'm MainActivity " + result);
                }
        );

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new DataFragment())
                    .commit();
        }
    }
}