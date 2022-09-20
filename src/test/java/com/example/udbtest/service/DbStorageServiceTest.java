package com.example.udbtest.service;

import com.example.udbtest.model.Dictionary;
import com.example.udbtest.model.FieldsType;
import com.example.udbtest.repository.DbRowRepo;
import com.example.udbtest.repository.DbStorageRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DbStorageServiceTest {

    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private DictionaryFieldsService dictionaryFieldsService;
    @Autowired
    private DbStorageService dbStorageService;
    @Autowired
    private DbStorageRepo dbStorageRepo;


    @Test
    void addEntryMap() {

        //Создание нового поля
        Dictionary testDic = dictionaryService.createDictionary("testDic");
        dictionaryFieldsService.createField("testDic", "testField1", FieldsType.STRING);
        dictionaryFieldsService.createField("testDic", "testField2", FieldsType.STRING);
        dictionaryFieldsService.createField("testDic", "testField3", FieldsType.STRING);

        Map<String, String> rowMap1 = new HashMap<>();
        rowMap1.put("testField1", "value field 1");
        rowMap1.put("testField2", "value field 2");
        rowMap1.put("testField3", "value field 3");

        Map<String, String> rowMap2 = new HashMap<>();
        rowMap2.put("testField1", "value field 1");
        rowMap2.put("testField2", "value field 2");
        rowMap2.put("testField3", "value field 3");

        Map<String, String> rowMap3 = new HashMap<>();
        rowMap3.put("testField1", "1value field 1");
        rowMap3.put("testField2", "2value field 2");
        rowMap3.put("testField3", "3value field 3");

        dbStorageService.addEntryMap("testDic", rowMap1);
        dbStorageService.addEntryMap("testDic", rowMap2);
        dbStorageService.addEntryMap("testDic", rowMap3);

        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("testField1", "value field 1");
        filterMap.put("testField2", "value field 2");

        Map<Long, Map<String, String>> resultMap = dbStorageService.getDbStorageMap("testDic", filterMap);

        assertEquals(2, resultMap.size());

    }

    @Test
    void deleteRow() {
        //Создание нового поля
        Dictionary testDic = dictionaryService.createDictionary("testDicDelete");
        dictionaryFieldsService.createField("testDicDelete", "testField1", FieldsType.STRING);
        dictionaryFieldsService.createField("testDicDelete", "testField2", FieldsType.STRING);
        dictionaryFieldsService.createField("testDicDelete", "testField3", FieldsType.STRING);

        Map<String, String> rowMap1 = new HashMap<>();
        rowMap1.put("testField1", "value field 1");
        rowMap1.put("testField2", "value field 2");
        rowMap1.put("testField3", "value field 3");

        Map<String, String> rowMap2 = new HashMap<>();
        rowMap2.put("testField1", "value field 1");
        rowMap2.put("testField2", "value field 2");
        rowMap2.put("testField3", "value field 3");

        Map<String, String> rowMap3 = new HashMap<>();
        rowMap3.put("testField1", "value field 1");
        rowMap3.put("testField2", "value field 2");
        rowMap3.put("testField3", "value field 3");

        dbStorageService.addEntryMap("testDicDelete", rowMap1);
        dbStorageService.addEntryMap("testDicDelete", rowMap2);
        dbStorageService.addEntryMap("testDicDelete", rowMap3);

        assertEquals(true, dbStorageService.deleteRow(1L));

    }

    @Test
    void updateRow() {

        //Создание нового поля
        Dictionary testDic = dictionaryService.createDictionary("testDicUpdate");
        dictionaryFieldsService.createField("testDicUpdate", "testField1", FieldsType.STRING);
        dictionaryFieldsService.createField("testDicUpdate", "testField2", FieldsType.STRING);
        dictionaryFieldsService.createField("testDicUpdate", "testField3", FieldsType.STRING);

        Map<String, String> rowMap1 = new HashMap<>();
        rowMap1.put("testField1", "value field 1");
        rowMap1.put("testField2", "value field 2");
        rowMap1.put("testField3", "value field 3");

        Map<String, String> rowMap2 = new HashMap<>();
        rowMap2.put("testField1", "value field 1");
        rowMap2.put("testField2", "value field 2");
        rowMap2.put("testField3", "value field 3");

        Map<String, String> rowMap3 = new HashMap<>();
        rowMap3.put("testField1", "value field 1");
        rowMap3.put("testField2", "value field 2");
        rowMap3.put("testField3", "value field 3");

        dbStorageService.addEntryMap("testDicUpdate", rowMap1);
        dbStorageService.addEntryMap("testDicUpdate", rowMap2);
        dbStorageService.addEntryMap("testDicUpdate", rowMap3);

        Map<String, String> rowMapEdit = new HashMap<>();
        rowMapEdit.put("testField1", "value1");
        rowMapEdit.put("testField2", "value2");
        rowMapEdit.put("testField3", "value3");

        assertEquals(true, dbStorageService.updateRow(1L, rowMapEdit));

    }




}