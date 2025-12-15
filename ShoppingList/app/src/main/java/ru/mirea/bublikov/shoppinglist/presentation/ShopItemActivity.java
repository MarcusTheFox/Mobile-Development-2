package ru.mirea.bublikov.shoppinglist.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.bublikov.domain.models.Currency;
import ru.mirea.bublikov.domain.models.CurrencyCode;
import ru.mirea.bublikov.domain.models.ShopItem;
import ru.mirea.bublikov.shoppinglist.R;

public class ShopItemActivity extends AppCompatActivity {

    private ShopItemViewModel shopItemViewModel;
    private CurrencyViewModel currencyViewModel;
    private CurrencyAdapter currencyAdapter;

    private EditText editTextName;
    private EditText editTextCount;
    private EditText editTextPrice;
    private Button buttonSave;
    private Spinner spinnerCurrency;
    private RecyclerView recyclerViewCurrencies;

    private int shopItemId = ShopItem.UNDEFINED_ID;
    private String screenMode = MODE_UNKNOWN;

    private static final String EXTRA_SCREEN_MODE = "extra_mode";
    private static final String EXTRA_SHOP_ITEM_ID = "extra_shop_item_id";
    private static final String MODE_ADD = "mode_add";
    private static final String MODE_EDIT = "mode_edit";
    private static final String MODE_UNKNOWN = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_shop_item);

        parseIntent();
        initViews();

        ViewModelFactory factory = new ViewModelFactory(this);
        shopItemViewModel = new ViewModelProvider(this, factory).get(ShopItemViewModel.class);
        currencyViewModel = new ViewModelProvider(this, factory).get(CurrencyViewModel.class);

        setupRecyclerView();
        observeViewModel();
        setupClickListeners();

        if (screenMode.equals(MODE_EDIT)) {
            setTitle("Редактировать товар");
        } else {
            setTitle("Добавить товар");
        }
    }

    private void setupRecyclerView() {
        recyclerViewCurrencies = findViewById(R.id.recyclerViewCurrencies);
        currencyAdapter = new CurrencyAdapter();
        recyclerViewCurrencies.setAdapter(currencyAdapter);
        recyclerViewCurrencies.setLayoutManager(new LinearLayoutManager(this));
    }

    private void observeViewModel() {
        shopItemViewModel.shouldCloseScreen.observe(this, shouldClose -> {
            if (shouldClose) {
                finish();
            }
        });

        currencyViewModel.getCurrencyList().observe(this, currencies -> {
            currencyAdapter.setItems(currencies);
            setupSpinner(currencies);

            if (screenMode.equals(MODE_EDIT)) {
                shopItemViewModel.getShopItem(shopItemId);
            }
        });

        shopItemViewModel.shopItem.observe(this, item -> {
            if (item != null) {
                editTextName.setText(item.getName());
                editTextCount.setText(String.valueOf(item.getCount()));
                editTextPrice.setText(String.valueOf(item.getPrice()));
                setSpinnerSelection(item.getCurrency());
            }
        });
    }

    private void setupClickListeners() {
        buttonSave.setOnClickListener(v -> {
            int selectedPosition = spinnerCurrency.getSelectedItemPosition();
            CurrencyCode selectedCode = CurrencyCode.values()[selectedPosition];

            shopItemViewModel.saveShopItem(
                    editTextName.getText().toString(),
                    editTextCount.getText().toString(),
                    editTextPrice.getText().toString(),
                    selectedCode
            );
        });
    }

    private void setupSpinner(List<Currency> currencies) {
        List<String> currencyNames = new ArrayList<>();
        for (Currency c : currencies) {
            currencyNames.add(c.getName());
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencyNames);
        spinnerCurrency.setAdapter(spinnerAdapter);
    }

    private void setSpinnerSelection(CurrencyCode currencyCode) {
        if (currencyCode != null) {
            int position = currencyCode.ordinal();
            spinnerCurrency.setSelection(position);
        }
    }

    private void initViews() {
        editTextName = findViewById(R.id.editTextName);
        editTextCount = findViewById(R.id.editTextCount);
        editTextPrice = findViewById(R.id.editTextPrice);
        spinnerCurrency = findViewById(R.id.spinnerCurrency);
        buttonSave = findViewById(R.id.buttonSave);
    }

    private void parseIntent() {
        if (!getIntent().hasExtra(EXTRA_SCREEN_MODE)) {
            throw new RuntimeException("Screen mode is absent");
        }
        String mode = getIntent().getStringExtra(EXTRA_SCREEN_MODE);
        if (!mode.equals(MODE_ADD) && !mode.equals(MODE_EDIT)) {
            throw new RuntimeException("Unknown screen mode " + mode);
        }
        screenMode = mode;
        if (screenMode.equals(MODE_EDIT)) {
            if (!getIntent().hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw new RuntimeException("Shop item id is absent");
            }
            shopItemId = getIntent().getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID);
        }
    }

    public static Intent newIntentAddItem(Context context) {
        Intent intent = new Intent(context, ShopItemActivity.class);
        intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD);
        return intent;
    }

    public static Intent newIntentEditItem(Context context, int shopItemId) {
        Intent intent = new Intent(context, ShopItemActivity.class);
        intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT);
        intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId);
        return intent;
    }
}