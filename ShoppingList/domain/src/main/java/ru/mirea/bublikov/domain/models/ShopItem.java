package ru.mirea.bublikov.domain.models;

import java.util.Objects;

public class ShopItem {
    public static final int UNDEFINED_ID = -1;

    private int id;
    private String name;
    private int count;
    private double price;
    private boolean enabled;

    public ShopItem(String name, int count, double price, boolean enabled) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.enabled = enabled;
        this.id = UNDEFINED_ID;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopItem shopItem = (ShopItem) o;
        return id == shopItem.id && count == shopItem.count &&
                enabled == shopItem.enabled &&
                Double.compare(shopItem.price, price) == 0 &&
                Objects.equals(name, shopItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, count, enabled, price);
    }
}