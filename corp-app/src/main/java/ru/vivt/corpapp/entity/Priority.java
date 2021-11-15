package ru.vivt.corpapp.entity;

public enum Priority {
    HIGEST("Блокирующий"),
    HIGH("Высокий"),
    MEDIUM("Средний"),
    LOW("Низкий");

    private String displayName;

    Priority(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
