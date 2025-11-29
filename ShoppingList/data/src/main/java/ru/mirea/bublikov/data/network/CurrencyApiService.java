package ru.mirea.bublikov.data.network;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.mirea.bublikov.data.network.model.CurrencyResponse;

public interface CurrencyApiService {
    @GET("v6/latest/RUB")
    Call<CurrencyResponse> getRates();
}
