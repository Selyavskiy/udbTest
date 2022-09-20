package com.example.udbtest.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class DictionaryFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private FieldsType fieldsType;

    @ManyToOne
    private Dictionary dictionary;
    @OneToMany
    private List<DbStorage> dbStorage;


    public DictionaryFields(Long id, String name, FieldsType fieldsType, Dictionary dictionary, List<DbStorage> dbStorage) {
        this.id = id;
        this.name = name;
        this.fieldsType = fieldsType;
        this.dictionary = dictionary;
        this.dbStorage = dbStorage;
    }

    public DictionaryFields() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldsType getFieldsType() {
        return fieldsType;
    }

    public void setFieldsType(FieldsType fieldsType) {
        this.fieldsType = fieldsType;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<DbStorage> getDbStorage() {
        return dbStorage;
    }

    public void setDbStorage(List<DbStorage> dbStorage) {
        this.dbStorage = dbStorage;
    }
}
