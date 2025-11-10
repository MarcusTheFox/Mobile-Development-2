package ru.mirea.bublikov.data.storage.database;

import androidx.room.TypeConverter;

import ru.mirea.bublikov.domain.models.CurrencyCode;

public class CurrencyTypeConverter {
    @TypeConverter
    public String fromCurrencyCode(CurrencyCode currencyCode) {
        return currencyCode == null ? null : currencyCode.name();
    }

    @TypeConverter
    public CurrencyCode toCurrencyCode(String currencyCodeString) {
        return currencyCodeString == null ? null : CurrencyCode.valueOf(currencyCodeString);
    }
}
