package ru.mirea.bublikov.recyclerviewapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<HistoricalEvent> events = getListData();
        EventAdapter adapter = new EventAdapter(events);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private List<HistoricalEvent> getListData() {
        List<HistoricalEvent> list = new ArrayList<>();
        list.add(new HistoricalEvent("753 г. до н.э.", "Основание Рима", "ic_rome"));
        list.add(new HistoricalEvent("1453 г.", "Падение Константинополя", "ic_fall"));
        list.add(new HistoricalEvent("1492 г.", "Христофор Колумб открывает Америку", "ic_columbus"));
        list.add(new HistoricalEvent("1961 г.", "Первый полет человека в космос", "ic_space"));
        list.add(new HistoricalEvent("1991 г.", "Создание первого веб-сайта (World Wide Web)", "ic_internet"));
        return list;
    }
}