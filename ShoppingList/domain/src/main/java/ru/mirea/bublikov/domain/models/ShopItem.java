package ru.mirea.bublikov.domain.models;

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