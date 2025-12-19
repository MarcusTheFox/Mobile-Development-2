package ru.mirea.bublikov.shoppinglist.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ru.mirea.bublikov.shoppinglist.R;

public class MainFragment extends Fragment {

    private MainViewModel mainViewModel;
    private ShopListAdapter shopListAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewShopList);
        progressBar = view.findViewById(R.id.progressBar);
        FloatingActionButton fabAddItem = view.findViewById(R.id.fabAddItem);

        ViewModelFactory factory = new ViewModelFactory(requireContext());
        mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        setupRecyclerView();

        mainViewModel.getShopList().observe(getViewLifecycleOwner(), list -> shopListAdapter.submitList(list));
        mainViewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            recyclerView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        });

        if (savedInstanceState == null) {
            mainViewModel.initialLoad();
        }

        fabAddItem.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putString("screen_mode", "mode_add");
            args.putInt("shop_item_id", -1);

            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_mainFragment_to_shopItemFragment, args);
        });

        shopListAdapter.setOnShopItemClickListener(shopItem -> {
            Bundle args = new Bundle();
            args.putString("screen_mode", "mode_edit");
            args.putInt("shop_item_id", shopItem.getId());

            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_mainFragment_to_shopItemFragment, args);
        });

        shopListAdapter.setOnDeleteItemClickListener(shopItem -> {
            mainViewModel.deleteShopItem(shopItem);
        });

        shopListAdapter.setOnMarkItemClickListener(shopItem -> {
            mainViewModel.markShopItem(shopItem);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mainViewModel.loadShopList();
    }

    private void setupRecyclerView() {
        shopListAdapter = new ShopListAdapter();
        recyclerView.setAdapter(shopListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}