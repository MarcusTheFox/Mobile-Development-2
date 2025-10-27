package ru.mirea.bublikov.shoppinglist.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.bublikov.domain.models.ShopItem;
import ru.mirea.bublikov.shoppinglist.R;

public class ShopItemActivity extends AppCompatActivity {

    private ShopItemViewModel viewModel;
    private EditText editTextName;
    private EditText editTextCount;
    private EditText editTextPrice;
    private Button buttonSave;

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
        setContentView(R.layout.activity_shop_item);

        parseIntent();
        initViews();

        ViewModelFactory factory = new ViewModelFactory(this);
        viewModel = new ViewModelProvider(this, factory).get(ShopItemViewModel.class);

        setupScreenMode();
        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.shouldCloseScreen.observe(this, shouldClose -> {
            if (shouldClose) {
                finish();
            }
        });
    }

    private void setupScreenMode() {
        if (screenMode.equals(MODE_EDIT)) {
            setTitle("Редактировать товар");
            viewModel.getShopItem(shopItemId);
            viewModel.shopItem.observe(this, item -> {
                if (item != null) {
                    editTextName.setText(item.getName());
                    editTextCount.setText(String.valueOf(item.getCount()));
                    editTextPrice.setText(String.valueOf(item.getPrice()));
                }
            });
        } else {
            setTitle("Добавить товар");
        }

        buttonSave.setOnClickListener(v ->
            viewModel.saveShopItem(
                editTextName.getText().toString(),
                editTextCount.getText().toString(),
                editTextPrice.getText().toString()
            )
        );
    }

    private void initViews() {
        editTextName = findViewById(R.id.editTextName);
        editTextCount = findViewById(R.id.editTextCount);
        editTextPrice = findViewById(R.id.editTextPrice);
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