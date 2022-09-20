package com.example.udbtest.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    private List<DictionaryFields> dictionaryFieldsList;


    public Dictionary(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Dictionary() {
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

    public List<DictionaryFields> getDictionaryFieldsList() {
        return dictionaryFieldsList;
    }

    public void setDictionaryFieldsList(List<DictionaryFields> dictionaryFieldsList) {
        this.dictionaryFieldsList = dictionaryFieldsList;
    }
}
