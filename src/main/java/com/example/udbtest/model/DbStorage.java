package com.example.udbtest.model;

import javax.persistence.*;

@Entity
public class DbStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;

    @ManyToOne
    private DictionaryFields dictionaryField;

    @ManyToOne
    private DbRow dbRow;

    public DbStorage(Long id, String value, DictionaryFields dictionaryField, DbRow dbRow) {
        this.id = id;
        this.value = value;
        this.dictionaryField = dictionaryField;
        this.dbRow = dbRow;
    }

    public DbStorage(String value, DictionaryFields dictionaryField, DbRow dbRow) {
        this.id = id;
        this.value = value;
        this.dictionaryField = dictionaryField;
        this.dbRow = dbRow;
    }



    public DbStorage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DictionaryFields getdictionaryField() {
        return dictionaryField;
    }

    public void setDictionaryField(DictionaryFields dictionaryField) {
        this.dictionaryField = dictionaryField;
    }

    public DictionaryFields getDictionaryField() {
        return dictionaryField;
    }

    public DbRow getDbRow() {
        return dbRow;
    }

    public void setDbRow(DbRow dbRow) {
        this.dbRow = dbRow;
    }
}
