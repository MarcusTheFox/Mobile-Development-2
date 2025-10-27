package ru.mirea.bublikov.shoppinglist.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ru.mirea.bublikov.shoppinglist.R;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    private ShopListAdapter shopListAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private FloatingActionButton fabAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewShopList);
        progressBar = findViewById(R.id.progressBar);
        fabAddItem = findViewById(R.id.fabAddItem);

        ViewModelFactory factory = new ViewModelFactory(this);
        mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        setupRecyclerView();
        observeViewModel();
        setupClickListeners();

        if (savedInstanceState == null) {
            mainViewModel.initialLoad();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainViewModel.loadShopList();
    }

    private void observeViewModel() {
        mainViewModel.getShopList().observe(this, list -> {
            shopListAdapter.submitList(list);
        });

        mainViewModel.getIsLoading().observe(this, isLoading -> {
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            recyclerView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        });
    }

    private void setupRecyclerView() {
        shopListAdapter = new ShopListAdapter();
        recyclerView.setAdapter(shopListAdapter);
    }

    private void setupClickListeners() {
        fabAddItem.setOnClickListener(v -> {
            Intent intent = ShopItemActivity.newIntentAddItem(this);
            startActivity(intent);
        });

        shopListAdapter.setOnShopItemClickListener(shopItem -> {
            Intent intent = ShopItemActivity.newIntentEditItem(this, shopItem.getId());
            startActivity(intent);
        });

        shopListAdapter.setOnMarkItemClickListener(shopItem -> {
            mainViewModel.markShopItem(shopItem);
        });

        shopListAdapter.setOnDeleteItemClickListener(shopItem -> {
            mainViewModel.deleteShopItem(shopItem);
        });
    }
}