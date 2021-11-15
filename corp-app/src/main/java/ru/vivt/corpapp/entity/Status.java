package ru.vivt.corpapp.entity;

public enum Status {
    OPEN("Открыт"),
    IN_PROGRESS("В работе"),
    DONE("Закрыт"),
    CANCELLED("Отменен");

    private String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
