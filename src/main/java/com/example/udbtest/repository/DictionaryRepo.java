package com.example.udbtest.repository;

import com.example.udbtest.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepo extends JpaRepository<Dictionary, Long> {

    Dictionary findByName(String name);

}
