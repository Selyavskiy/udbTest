package com.example.udbtest.service;

import com.example.udbtest.model.Dictionary;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DictionaryServiceTest {

    @Autowired
    private DictionaryService dictionaryService;

    @Test
    void createDictionary() {
        //Создание справочника, который еще не существует
        Dictionary dic = dictionaryService.createDictionary("new test dic");
        assertEquals("new test dic", dic.getName());

        //Попытка создания справочника, который уже существует
        Dictionary dicDouble = dictionaryService.createDictionary("new test dic");
        assertEquals(null, dicDouble);

        //Попытка создания справочника без имени
        Dictionary dicEmpty = dictionaryService.createDictionary("");
        assertEquals(null, dicEmpty);

        //Попытка создания справочника с именем null
        Dictionary dicNull = dictionaryService.createDictionary(null);
        assertEquals(null, dicNull);

    }


    @Test
    void deleteDictionary() {

        //Создание справочника, который еще не существует
        Dictionary dic = dictionaryService.createDictionary("new test dic");
        dictionaryService.deleteDictionary("new test dic");
        assertEquals(null, dictionaryService.getDictionary("new test dic"));

    }


}