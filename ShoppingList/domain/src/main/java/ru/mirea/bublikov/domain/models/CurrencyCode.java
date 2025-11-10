package ru.mirea.bublikov.domain.models;

public enum CurrencyCode {
    RUB("Российский рубль", "flag_of_russia"),
    USD("Доллар США", "flag_of_the_united_states"),
    EUR("Евро", "flag_of_europe"),
    CNY("Китайский юань", "flag_of_china");

    private final String fullName;
    private final String flagName;

    CurrencyCode(String fullName, String flagName) {
        this.fullName = fullName;
        this.flagName = flagName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getFlagName() {
        return flagName;
    }
}