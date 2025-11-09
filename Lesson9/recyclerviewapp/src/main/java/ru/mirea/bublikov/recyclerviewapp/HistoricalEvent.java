package ru.mirea.bublikov.recyclerviewapp;

public class HistoricalEvent {
    private String name;
    private String description;
    private String iconName;

    public HistoricalEvent(String name, String description, String iconName) {
        this.name = name;
        this.description = description;
        this.iconName = iconName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIconName() {
        return iconName;
    }
}
