# Отчёт 1

## **Lesson9**
Создан новый проект Lesson9.
- **Domain Layer:** Определены сущность Movie, интерфейс MovieRepository и два Use Case: GetFavoriteFilmUseCase и SaveMovieToFavoriteUseCase.
- **Data Layer:** Создан класс MovieRepositoryImpl, реализующий интерфейс MovieRepository. Для хранения данных используется SharedPreferences, доступ к которому осуществляется через Context, передаваемый из Presentation слоя, минуя Domain.
- **Presentation Layer:** MainActivity содержит UI и логику для взаимодействия с пользователем. Инициализация всех компонентов происходит в методе onCreate для корректной работы с Context.

### Экраны
<img width="569" height="1191" alt="1 - Lesson9 - 1" src="https://github.com/user-attachments/assets/c1e155b9-5e66-4a3b-b3c0-eff6d4682fb8" />

<img width="569" height="1184" alt="1 - Lesson9 - 2" src="https://github.com/user-attachments/assets/a71094fe-5f63-4a43-bf26-68ccad40abd1" />

<img width="563" height="1186" alt="1 - Lesson9 - 3" src="https://github.com/user-attachments/assets/465acfe7-5769-49d1-bc26-b7767a0cd191" />

<img width="555" height="1182" alt="1 - Lesson9 - 4" src="https://github.com/user-attachments/assets/01f6b5db-ae94-4ef4-8da0-e9ec4fa38d03" />

### Код
#### data/repository/MovieRepositoryImpl.java
``` java
package ru.mirea.bublikov.movieproject.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import ru.mirea.bublikov.movieproject.domain.models.Movie;
import ru.mirea.bublikov.movieproject.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {
    private static final String PREFERENCES_NAME = "movie_prefs";
    private static final String KEY_MOVIE_NAME = "movie_name";
    private static final String DEFAULT_MOVIE_NAME = "Фильм не сохранен";

    private final SharedPreferences sharedPreferences;

    public MovieRepositoryImpl(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean saveMovie(Movie movie) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_MOVIE_NAME, movie.getName());
        editor.apply();
        return true;
    }

    @Override
    public Movie getMovie() {
        String movieName = sharedPreferences.getString(KEY_MOVIE_NAME, DEFAULT_MOVIE_NAME);
        return new Movie(1, movieName);
    }
}

```

#### domain/models/Movie.java
``` java
package ru.mirea.bublikov.movieproject.domain.models;  
  
public class Movie {  
    private int id;  
    private String name;  
  
    public Movie(int id, String name) {  
        this.id = id;  
        this.name = name;  
    }  
  
    public String getName() {  
        return name;  
    }  
}
```

#### domain/repository/MovieRepository.java
``` java
package ru.mirea.bublikov.movieproject.domain.repository;

import ru.mirea.bublikov.movieproject.domain.models.Movie;

public interface MovieRepository {
    boolean saveMovie(Movie movie);
    Movie getMovie();
}
```

#### domain/usecases/GetFavoriteFilmUseCase.java
``` java
package ru.mirea.bublikov.movieproject.domain.usecases;

import ru.mirea.bublikov.movieproject.domain.models.Movie;
import ru.mirea.bublikov.movieproject.domain.repository.MovieRepository;

public class GetFavoriteFilmUseCase {
    private final MovieRepository movieRepository;

    public GetFavoriteFilmUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie execute() {
        return movieRepository.getMovie();
    }
}
```

#### domain/usecases/SaveMovieToFavoriteUseCase.java
``` java
package ru.mirea.bublikov.movieproject.domain.usecases;

import ru.mirea.bublikov.movieproject.domain.models.Movie;
import ru.mirea.bublikov.movieproject.domain.repository.MovieRepository;

public class SaveMovieToFavoriteUseCase {
    private final MovieRepository movieRepository;

    public SaveMovieToFavoriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public boolean execute(Movie movie) {
        return movieRepository.saveMovie(movie);
    }
}
```

#### presentation/MainActivity.java
``` java
package ru.mirea.bublikov.movieproject.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.mirea.bublikov.movieproject.R;
import ru.mirea.bublikov.movieproject.data.repository.MovieRepositoryImpl;
import ru.mirea.bublikov.movieproject.domain.models.Movie;
import ru.mirea.bublikov.movieproject.domain.repository.MovieRepository;
import ru.mirea.bublikov.movieproject.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.bublikov.movieproject.domain.usecases.SaveMovieToFavoriteUseCase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieRepository movieRepository = new MovieRepositoryImpl(this);
        GetFavoriteFilmUseCase getFavoriteFilmUseCase = new GetFavoriteFilmUseCase(movieRepository);
        SaveMovieToFavoriteUseCase saveMovieToFavoriteUseCase = new SaveMovieToFavoriteUseCase(movieRepository);

        TextView textViewMovie = findViewById(R.id.textViewMovie);
        EditText editTextMovie = findViewById(R.id.editTextMovie);
        Button buttonSaveMovie = findViewById(R.id.buttonSaveMovie);
        Button buttonGetMovie = findViewById(R.id.buttonGetMovie);

        buttonSaveMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editTextMovie.getText().toString();
                Movie movie = new Movie(2, text);
                boolean result = saveMovieToFavoriteUseCase.execute(movie);
                textViewMovie.setText(String.format("Save result %s", result));
            }
        });

        buttonGetMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie = getFavoriteFilmUseCase.execute();
                textViewMovie.setText(String.format("Movie name: %s", movie.getName()));
            }
        });
    }
}
```

## **ShoppingList**
Создан проект ShoppingList, представляющий собой приложение для ведения списка покупок.
- **Проектирование:** Разработаны диаграмма вариантов использования и диаграмма слоев, описывающие архитектуру и функционал приложения.
- **Domain Layer:** Определена ключевая сущность ShopItem и интерфейс ShoppingListRepository. Создан набор классов Use Case для каждой операции (добавление, удаление, редактирование, получение списка, отметка о покупке).
- **Data Layer:** Создан класс ShoppingListRepositoryImpl, который предоставляет тестовый набор данных для демонстрации работы логики приложения.
- **Presentation Layer:** MainActivity использует GetShoppingListUseCase для получения списка покупок и отображает его в TextView.

### Диаграммы
<img width="1952" height="528" alt="1 - ShoppingList - 1" src="https://github.com/user-attachments/assets/badd7fa4-6989-4e3b-9bbc-d4aec40a0029" />

<img width="2475" height="1077" alt="1 - ShoppingList - 2" src="https://github.com/user-attachments/assets/c7234b2e-7057-4308-834f-14eb88285578" />

### Экраны
<img width="650" height="1218" alt="1 - ShoppingList - 3" src="https://github.com/user-attachments/assets/3732d7ea-9975-4e1c-a4d9-4bf6ef34a21e" />

<img width="572" height="1190" alt="1 - ShoppingList - 4" src="https://github.com/user-attachments/assets/24fe522b-3844-499b-a9fc-b5f8bc6fe253" />

### Код
#### data/repository/ShoppingListRepositoryImpl.java
``` java
package ru.mirea.bublikov.shoppinglist.data.repository;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.bublikov.shoppinglist.domain.models.ShopItem;
import ru.mirea.bublikov.shoppinglist.domain.repository.ShoppingListRepository;

public class ShoppingListRepositoryImpl implements ShoppingListRepository {
    private final List<ShopItem> shopList = new ArrayList<>();
    private int autoIncrementId = 0;

    public ShoppingListRepositoryImpl() {
        addShopItem(new ShopItem("Хлеб", 1, true));
        addShopItem(new ShopItem("Молоко", 2, false));
        addShopItem(new ShopItem("Сыр", 1, false));
    }

    @Override
    public void addShopItem(ShopItem shopItem) {
        if (shopItem.getId() == ShopItem.UNDEFINED_ID) {
            shopItem.setId(autoIncrementId++);
        }
        shopList.add(shopItem);
    }

    @Override
    public void deleteShopItem(ShopItem shopItem) {
        shopList.remove(shopItem);
    }

    @Override
    public void editShopItem(ShopItem shopItem) {
        ShopItem oldItem = getShopItem(shopItem.getId());
        shopList.remove(oldItem);
        shopList.add(shopItem);
    }

    @Override
    public void markShopItem(ShopItem shopItem) {
        ShopItem itemInList = getShopItem(shopItem.getId());
        if (itemInList != null) {
            itemInList.setEnabled(!itemInList.isEnabled());
        }
    }

    @Override
    public ShopItem getShopItem(int shopItemId) {
        for (ShopItem item : shopList) {
            if (item.getId() == shopItemId) {
                return item;
            }
        }
        return null;
    }

    @Override
    public List<ShopItem> getShopList() {
        return new ArrayList<>(shopList);
    }
}
```

#### domain/models/ShopItem.java
``` java
package ru.mirea.bublikov.shoppinglist.domain.models;

public class ShopItem {
    public static final int UNDEFINED_ID = -1;

    private int id;
    private String name;
    private int count;
    private boolean enabled;

    public ShopItem(String name, int count, boolean enabled) {
        this.name = name;
        this.count = count;
        this.enabled = enabled;
        this.id = UNDEFINED_ID;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
```

#### domain/repository/ShoppingListRepository.java
``` java
package ru.mirea.bublikov.shoppinglist.domain.repository;

import java.util.List;

import ru.mirea.bublikov.shoppinglist.domain.models.ShopItem;

public interface ShoppingListRepository {
    void addShopItem(ShopItem shopItem);
    void deleteShopItem(ShopItem shopItem);
    void editShopItem(ShopItem shopItem);
    void markShopItem(ShopItem shopItem);
    ShopItem getShopItem(int shopItemId);
    List<ShopItem> getShopList();
}
```

#### domain/usecases/GetShoppingListUseCase.java
``` java
package ru.mirea.bublikov.shoppinglist.domain.usecases;

import java.util.List;

import ru.mirea.bublikov.shoppinglist.domain.models.ShopItem;
import ru.mirea.bublikov.shoppinglist.domain.repository.ShoppingListRepository;

public class GetShoppingListUseCase {
    private final ShoppingListRepository repository;

    public GetShoppingListUseCase(ShoppingListRepository repository) {
        this.repository = repository;
    }

    public List<ShopItem> execute() {
        return repository.getShopList();
    }
}
```

#### presentation/MainActivity.java
``` java
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
```

