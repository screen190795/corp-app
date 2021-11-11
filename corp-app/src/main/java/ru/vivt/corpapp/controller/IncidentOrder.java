package ru.vivt.corpapp.controller;

public enum IncidentOrder {

    ID("id"), // default
    TITLE("title"),
    PRIORITY("priority"),
    STATUS("status"),
    REPORTER_ID("reporter"),
    ASSIGNEE_ID("assignee"),
    COMMENT("comment");


    private final String fieldName;

        IncidentOrder(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldName() {
            return fieldName;
        }
}
