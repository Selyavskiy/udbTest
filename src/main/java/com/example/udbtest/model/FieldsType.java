package com.example.udbtest.model;

public enum FieldsType {
    STRING ("String"),
    BYTE("Byte"),
    SHORT("Short"),
    INTEGER("Integer"),
    LONG("Long"),
    FLOAT("Float"),
    DOUBLE("Double"),
    CHAR("Char"),
    BOOLEAN("Boolean"),
    DATE("Date");

    private String fieldsType;

    FieldsType(String fieldsType) {
        this.fieldsType = fieldsType;
    }

    @Override
    public String toString() {
        return fieldsType;
    }
}
