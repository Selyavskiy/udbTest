package com.example.udbtest.service;

import com.example.udbtest.model.DbStorage;
import com.example.udbtest.model.Dictionary;
import com.example.udbtest.model.DictionaryFields;
import com.example.udbtest.model.FieldsType;
import com.example.udbtest.repository.DbStorageRepo;
import com.example.udbtest.repository.DictionaryFieldsRepo;
import com.example.udbtest.repository.DictionaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DictionaryFieldsService {

    private DictionaryFieldsRepo dictionaryFieldsRepo;
    private DictionaryRepo dictionaryRepo;
    private DbStorageRepo dbStorageRepo;


    public DictionaryFields createField(String dictionaryName, String fieldName, FieldsType fieldType){
        if("".equals(dictionaryName) || "".equals(fieldName) ||
            dictionaryName == null || fieldName == null || fieldType == null){
            return null;
        }

        List<DictionaryFields> dicFieldsList = getFieldsDictionary(dictionaryName);
        Boolean isContains = false;

        for(DictionaryFields dicField : dicFieldsList){
            if(dicField.getName().equals(fieldName)){
                isContains = true;
            }
        }

        if(!isContains) {
            Dictionary dictionary = dictionaryRepo.findByName(dictionaryName);
            DictionaryFields dictionaryField = new DictionaryFields();

            dictionaryField.setName(fieldName);
            dictionaryField.setFieldsType(fieldType);
            dictionaryField.setDictionary(dictionary);

            dictionaryFieldsRepo.saveAndFlush(dictionaryField);
            dictionaryRepo.flush();

            return dictionaryField;
        }else{
            return null;
        }
    }

    public DictionaryFields renameField(String dictionaryName, String oldFieldName, String newFieldName){
        Dictionary dictionary = dictionaryRepo.findByName(dictionaryName);
        DictionaryFields dictionaryField = dictionaryFieldsRepo.findByNameAndDictionary(oldFieldName, dictionary);

        dictionaryField.setName(newFieldName);

        dictionaryFieldsRepo.saveAndFlush(dictionaryField);

        return dictionaryField;
    }

    public Boolean deleteField(String dictionaryName, String fieldName){
        Boolean result = false;
        Dictionary dictionary = dictionaryRepo.findByName(dictionaryName);
        DictionaryFields dictionaryField = dictionaryFieldsRepo.findByNameAndDictionary(fieldName, dictionary);
        List<DbStorage> dbStorageList = dbStorageRepo.findDbStoragesByDictionaryField(dictionaryField);

        dbStorageRepo.deleteAll(dbStorageList);
        dictionaryFieldsRepo.delete(dictionaryField);

        return result;
    }

    public List<DictionaryFields> getFieldsDictionary(String dicName){
        Dictionary dictionary = dictionaryRepo.findByName(dicName);

        List<DictionaryFields> dictionaryFieldsList = dictionaryFieldsRepo.findByDictionary(dictionary);

        return dictionaryFieldsList;
    }

    @Autowired
    public DictionaryFieldsService(DictionaryFieldsRepo dictionaryFieldsRepo, DictionaryRepo dictionaryRepo, DbStorageRepo dbStorageRepo) {
        this.dictionaryFieldsRepo = dictionaryFieldsRepo;
        this.dictionaryRepo = dictionaryRepo;
        this.dbStorageRepo = dbStorageRepo;
    }

    public DictionaryFieldsRepo getDictionaryFieldsRepo() {
        return dictionaryFieldsRepo;
    }

    public void setDictionaryFieldsRepo(DictionaryFieldsRepo dictionaryFieldsRepo) {
        this.dictionaryFieldsRepo = dictionaryFieldsRepo;
    }

    public DictionaryRepo getDictionaryRepo() {
        return dictionaryRepo;
    }

    public void setDictionaryRepo(DictionaryRepo dictionaryRepo) {
        this.dictionaryRepo = dictionaryRepo;
    }
}
