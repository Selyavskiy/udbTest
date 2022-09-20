package com.example.udbtest.repository;

import com.example.udbtest.model.Dictionary;
import com.example.udbtest.model.DictionaryFields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryFieldsRepo extends JpaRepository<DictionaryFields, Long> {

    DictionaryFields findByNameAndDictionary (String name, Dictionary dictionary);

    List<DictionaryFields> findByDictionary(Dictionary dictionary);


}
