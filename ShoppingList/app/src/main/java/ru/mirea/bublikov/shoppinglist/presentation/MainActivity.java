package ru.mirea.bublikov.shoppinglist.presentation;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ru.mirea.bublikov.shoppinglist.R;
import ru.mirea.bublikov.shoppinglist.data.repository.ShoppingListRepositoryImpl;
import ru.mirea.bublikov.shoppinglist.domain.models.ShopItem;
import ru.mirea.bublikov.shoppinglist.domain.repository.ShoppingListRepository;
import ru.mirea.bublikov.shoppinglist.domain.usecases.GetShoppingListUseCase;

public class MainActivity extends AppCompatActivity {
    private GetShoppingListUseCase getShoppingListUseCase;

    private TextView tvShopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShoppingListRepository repository = new ShoppingListRepositoryImpl();
        getShoppingListUseCase = new GetShoppingListUseCase(repository);

        tvShopList = findViewById(R.id.tv_shop_list);

        showShopList();
    }

    private void showShopList() {
        List<ShopItem> shopList = getShoppingListUseCase.execute();

        StringBuilder sb = new StringBuilder();
        for (ShopItem item : shopList) {
            sb.append(item.getName())
                    .append(", кол-во: ").append(item.getCount())
                    .append(", куплен: ").append(item.isEnabled())
                    .append("\n\n");
        }
        tvShopList.setText(sb.toString());
    }
}