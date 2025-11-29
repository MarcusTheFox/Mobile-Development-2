package ru.mirea.bublikov.data.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mirea.bublikov.data.network.CurrencyApiService;
import ru.mirea.bublikov.data.network.model.CurrencyResponse;
import ru.mirea.bublikov.domain.models.Currency;
import ru.mirea.bublikov.domain.models.CurrencyCode;
import ru.mirea.bublikov.domain.repository.CurrencyRepository;

public class NetworkCurrencyRepositoryImpl implements CurrencyRepository {
    private final CurrencyApiService apiService;
    private final String BASE_URL = "https://open.er-api.com/";

    public NetworkCurrencyRepositoryImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(CurrencyApiService.class);
    }

    @Override
    public List<Currency> getCurrencyRates() {
        List<Currency> resultList = new ArrayList<>();

        try {
            Response<CurrencyResponse> response = apiService.getRates().execute();

            if (response.isSuccessful() && response.body() != null) {
                Map<String, Double> rates = response.body().getRates();
                for (CurrencyCode code : CurrencyCode.values()) {
                    Double rate = rates.get(code.name());
                    if (rate != null) {
                        double rubRate = 1.0 / rate;
                        resultList.add(new Currency(code, rubRate));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
