package ru.mirea.bublikov.listviewapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        String[] bookList = generateBookList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                bookList
        );
        listView.setAdapter(adapter);
    }

    private String[] generateBookList() {
        return new String[]{
                "1. Джордж Оруэлл - 1984",
                "2. Рэй Брэдбери - 451 градус по Фаренгейту",
                "3. Михаил Булгаков - Мастер и Маргарита",
                "4. Федор Достоевский - Преступление и наказание",
                "5. Антуан де Сент-Экзюпери - Маленький принц",
                "6. Лев Толстой - Война и мир",
                "7. Эрих Мария Ремарк - Три товарища",
                "8. Оскар Уайльд - Портрет Дориана Грея",
                "9. Джером Сэлинджер - Над пропастью во ржи",
                "10. Харпер Ли - Убить пересмешника",
                "11. Фрэнсис Скотт Фицджеральд - Великий Гэтсби",
                "12. Габриэль Гарсиа Маркес - Сто лет одиночества",
                "13. Джейн Остин - Гордость и предубеждение",
                "14. Даниэль Киз - Цветы для Элджернона",
                "15. Кен Кизи - Пролетая над гнездом кукушки",
                "16. Артур Конан Дойл - Приключения Шерлока Холмса",
                "17. Виктор Гюго - Отверженные",
                "18. Эрнест Хемингуэй - Старик и море",
                "19. Уильям Голдинг - Повелитель мух",
                "20. Альбер Камю - Посторонний",
                "21. Курт Воннегут - Бойня номер пять",
                "22. Джон Стейнбек - Гроздья гнева",
                "23. Патрик Зюскинд - Парфюмер",
                "24. Колин Маккалоу - Поющие в терновнике",
                "25. Александр Дюма - Граф Монте-Кристо",
                "26. Маргарет Митчелл - Унесенные ветром",
                "27. Чак Паланик - Бойцовский клуб",
                "28. Станислав Лем - Солярис",
                "29. Аркадий и Борис Стругацкие - Пикник на обочине",
                "30. Дуглас Адамс - Автостопом по галактике",
                "31. Терри Пратчетт - Цвет волшебства"
        };
    }
}