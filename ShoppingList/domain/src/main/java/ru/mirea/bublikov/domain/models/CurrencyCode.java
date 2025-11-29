package ru.mirea.bublikov.domain.models;

public enum CurrencyCode {
    RUB("Российский рубль", "ru"),
    USD("Доллар США", "us"),
    EUR("Евро", "eu"),
    CNY("Китайский юань", "cn");

    private final String fullName;
    private final String countryCode;

    CurrencyCode(String fullName, String countryCode) {
        this.fullName = fullName;
        this.countryCode = countryCode;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCountryCode() {
        return countryCode;
    }
}