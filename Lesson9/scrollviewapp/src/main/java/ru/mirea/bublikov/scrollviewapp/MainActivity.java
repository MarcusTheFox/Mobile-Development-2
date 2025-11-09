package ru.mirea.bublikov.scrollviewapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout container = findViewById(R.id.container);
        LayoutInflater inflater = getLayoutInflater();

        for (int i = 1; i <= 100; i++) {
            View itemView = inflater.inflate(R.layout.item, container, false);
            TextView textView = itemView.findViewById(R.id.textView);
            textView.setText(String.format("Элемент %d", i));
            container.addView(itemView);
        }
    }
}