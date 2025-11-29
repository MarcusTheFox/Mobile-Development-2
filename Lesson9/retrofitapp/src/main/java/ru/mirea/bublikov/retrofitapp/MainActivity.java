package ru.mirea.bublikov.retrofitapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        fetchTodos();
    }

    private void fetchTodos() {
        apiService.getTodos().enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Todo> todos = response.body();
                    todoAdapter = new TodoAdapter(MainActivity.this, todos);
                    todoAdapter.setOnTodoCheckedChangeListener((todo, isChecked) -> {
                        updateTodoStatus(todo, isChecked);
                    });
                    recyclerView.setAdapter(todoAdapter);
                } else {
                    Log.e(TAG, "onResponse (fetch): " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable throwable) {
                Log.e(TAG, "onFailure (fetch): " + throwable.getMessage());
            }
        });
    }

    private void updateTodoStatus(Todo todo, boolean isChecked) {
        todo.setCompleted(isChecked);

        apiService.updateTodo(todo.getId(), todo).enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "todo " + todo.getId() + " now is " + isChecked, Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "onResponse (update): " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable throwable) {
                Log.e(TAG, "onFailure (update): " + throwable.getMessage());
            }
        });
    }
}