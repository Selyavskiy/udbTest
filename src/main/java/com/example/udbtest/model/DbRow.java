package com.example.udbtest.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class DbRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<DbStorage> dbStorageList;

    public DbRow() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DbStorage> getDbStorageList() {
        return dbStorageList;
    }

    public void setDbStorageList(List<DbStorage> dbStorageList) {
        this.dbStorageList = dbStorageList;
    }
}
