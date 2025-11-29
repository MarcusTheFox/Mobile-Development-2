package ru.mirea.bublikov.domain.models;

public class Currency {
    private CurrencyCode code;
    private double rate;

    public Currency(CurrencyCode code, double rate) {
        this.code = code;
        this.rate = rate;
    }

    public CurrencyCode getCode() { return code; }
    public String getName() { return code.getFullName(); }
    public double getRate() { return rate; }
}