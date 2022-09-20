package com.example.udbtest.repository;

import com.example.udbtest.model.DbRow;
import com.example.udbtest.model.DbStorage;
import com.example.udbtest.model.DictionaryFields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DbStorageRepo extends JpaRepository<DbStorage, Long> {
    List<DbStorage> findDbStoragesByDictionaryFieldIn (List<DictionaryFields> dictionaryFieldList);
    List<DbStorage> findDbStoragesByDictionaryField (DictionaryFields dictionaryField);
    List<DbStorage> findDbStoragesByDictionaryFieldAndValue(DictionaryFields dictionaryFields, String value);
    List<DbStorage> findDbStoragesByDbRowIn(List<DbRow> dbRowList);
    List<DbStorage> findDbStoragesByDbRow(DbRow dbRow);


}
