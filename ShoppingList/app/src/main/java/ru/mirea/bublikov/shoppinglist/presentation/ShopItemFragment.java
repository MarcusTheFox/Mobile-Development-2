package ru.mirea.bublikov.shoppinglist.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import ru.mirea.bublikov.domain.models.Currency;
import ru.mirea.bublikov.domain.models.CurrencyCode;
import ru.mirea.bublikov.domain.models.ShopItem;
import ru.mirea.bublikov.shoppinglist.R;

public class ShopItemFragment extends Fragment {

    private static final String SCREEN_MODE = "extra_mode";
    private static final String SHOP_ITEM_ID = "extra_shop_item_id";
    private static final String MODE_ADD = "mode_add";
    private static final String MODE_EDIT = "mode_edit";

    private ShopItemViewModel shopItemViewModel;
    private CurrencyViewModel currencyViewModel;

    private EditText editTextName, editTextCount, editTextPrice;
    private Spinner spinnerCurrency;
    private RecyclerView recyclerViewCurrencies;
    private CurrencyAdapter currencyAdapter;

    private String screenMode = "";
    private int shopItemId = ShopItem.UNDEFINED_ID;

    public static ShopItemFragment newInstanceAddItem() {
        Bundle args = new Bundle();
        args.putString(SCREEN_MODE, MODE_ADD);
        ShopItemFragment fragment = new ShopItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ShopItemFragment newInstanceEditItem(int shopItemId) {
        Bundle args = new Bundle();
        args.putString(SCREEN_MODE, MODE_EDIT);
        args.putInt(SHOP_ITEM_ID, shopItemId);
        ShopItemFragment fragment = new ShopItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        parseParams();
        initViews(view);

        ViewModelFactory factory = new ViewModelFactory(requireContext());
        shopItemViewModel = new ViewModelProvider(this, factory).get(ShopItemViewModel.class);
        currencyViewModel = new ViewModelProvider(this, factory).get(CurrencyViewModel.class);

        setupRecyclerView();
        observeViewModels();

        view.findViewById(R.id.buttonSave).setOnClickListener(v -> {
            int selectedPosition = spinnerCurrency.getSelectedItemPosition();
            if (selectedPosition >= 0) {
                CurrencyCode selectedCode = CurrencyCode.values()[selectedPosition];
                shopItemViewModel.saveShopItem(
                        editTextName.getText().toString(),
                        editTextCount.getText().toString(),
                        editTextPrice.getText().toString(),
                        selectedCode
                );
            }
        });
    }

    private void observeViewModels() {
        shopItemViewModel.shouldCloseScreen.observe(getViewLifecycleOwner(), shouldClose -> {
            if (shouldClose) {
                getParentFragmentManager().popBackStack();
            }
        });

        currencyViewModel.getCurrencyList().observe(getViewLifecycleOwner(), currencies -> {
            currencyAdapter.setItems(currencies);
            setupSpinner(currencies);
            if (screenMode.equals(MODE_EDIT)) {
                shopItemViewModel.getShopItem(shopItemId);
            }
        });

        shopItemViewModel.shopItem.observe(getViewLifecycleOwner(), item -> {
            if (item != null) {
                editTextName.setText(item.getName());
                editTextCount.setText(String.valueOf(item.getCount()));
                editTextPrice.setText(String.valueOf(item.getPrice()));
                setSpinnerSelection(item.getCurrency());
            }
        });
    }

    private void parseParams() {
        Bundle args = getArguments();
        if (args == null || !args.containsKey(SCREEN_MODE)) {
            throw new RuntimeException("Param screen mode is absent");
        }
        screenMode = args.getString(SCREEN_MODE);
        if (screenMode.equals(MODE_EDIT)) {
            if (!args.containsKey(SHOP_ITEM_ID)) {
                throw new RuntimeException("Param shop item id is absent");
            }
            shopItemId = args.getInt(SHOP_ITEM_ID);
        }
    }

    private void initViews(View view) {
        editTextName = view.findViewById(R.id.editTextName);
        editTextCount = view.findViewById(R.id.editTextCount);
        editTextPrice = view.findViewById(R.id.editTextPrice);
        spinnerCurrency = view.findViewById(R.id.spinnerCurrency);
        recyclerViewCurrencies = view.findViewById(R.id.recyclerViewCurrencies);
    }

    private void setupRecyclerView() {
        currencyAdapter = new CurrencyAdapter();
        recyclerViewCurrencies.setAdapter(currencyAdapter);
        recyclerViewCurrencies.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setupSpinner(List<Currency> currencies) {
        List<String> currencyNames = new ArrayList<>();
        for (Currency c : currencies) {
            currencyNames.add(c.getName());
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, currencyNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrency.setAdapter(spinnerAdapter);
    }

    private void setSpinnerSelection(CurrencyCode currencyCode) {
        if (currencyCode != null && spinnerCurrency.getAdapter() != null) {
            int position = currencyCode.ordinal();
            spinnerCurrency.setSelection(position);
        }
    }
}