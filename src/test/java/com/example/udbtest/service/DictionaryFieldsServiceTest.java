package com.example.udbtest.service;

import com.example.udbtest.model.Dictionary;
import com.example.udbtest.model.DictionaryFields;
import com.example.udbtest.model.FieldsType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class DictionaryFieldsServiceTest {

    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private DictionaryFieldsService dictionaryFieldsService;


    @Test
    void renameField() {
        //Создание нового поля и его переименование
        Dictionary testDic = dictionaryService.createDictionary("testDic");
        dictionaryFieldsService.createField("testDic", "testField", FieldsType.STRING);
        dictionaryFieldsService.renameField("testDic", "testField", "newTestField");
        List<DictionaryFields> testList = dictionaryFieldsService.getFieldsDictionary("testDic");
        assertEquals(testList.get(0).getName(), "newTestField");

        //Попытка создания уже существующего поля
        DictionaryFields doubleField = dictionaryFieldsService.createField("testDic", "newTestField", FieldsType.STRING);
        assertEquals(doubleField, null);

        //Попытка создания уже существующего поля
        DictionaryFields emptyField = dictionaryFieldsService.createField("testDic", "", FieldsType.STRING);
        assertEquals(emptyField, null);

    }

}