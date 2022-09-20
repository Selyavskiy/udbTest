package com.example.udbtest.service;

import com.example.udbtest.model.DbStorage;
import com.example.udbtest.model.Dictionary;
import com.example.udbtest.model.DictionaryFields;
import com.example.udbtest.repository.DbStorageRepo;
import com.example.udbtest.repository.DictionaryFieldsRepo;
import com.example.udbtest.repository.DictionaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService {

    private DictionaryRepo dictionaryRepo;
    private DictionaryFieldsRepo dictionaryFieldsRepo;
    private DbStorageRepo dbStorageRepo;

    public Dictionary createDictionary(String name){
        if(name == null || "".equals(name)){
            return null;
        }

        Dictionary resultDic = dictionaryRepo.findByName(name);
        if(resultDic==null) {
            resultDic = new Dictionary();
            resultDic.setName(name);
            dictionaryRepo.saveAndFlush(resultDic);
            return resultDic;
        }else{
            return null;
        }
    }

    public Dictionary getDictionary(String name){
        Dictionary dictionary = dictionaryRepo.findByName(name);
        return dictionary;
    }

    public Boolean deleteDictionary(String name){
        Boolean result = false;
        Dictionary deleteDictionary = dictionaryRepo.findByName(name);
        List<DictionaryFields> dictionaryFieldList = dictionaryFieldsRepo.findByDictionary(deleteDictionary);
        List<DbStorage> dbStorageList = dbStorageRepo.findDbStoragesByDictionaryFieldIn(dictionaryFieldList);

        dbStorageRepo.deleteAll(dbStorageList);
        dictionaryFieldsRepo.deleteAll(dictionaryFieldList);
        dictionaryRepo.delete(deleteDictionary);

        return result;
    }

    public Boolean renameDictionary(String oldName, String newName){
        Boolean result = false;
        Dictionary renameDictionary = dictionaryRepo.findByName(oldName);
        renameDictionary.setName(newName);
        dictionaryRepo.saveAndFlush(renameDictionary);
        return result;
    }


    private Boolean checkDictionaryName(String name){
        Boolean result = false;

        return result;
    }

    @Autowired
    public DictionaryService(DictionaryRepo dictionaryRepo, DictionaryFieldsRepo dictionaryFieldsRepo, DbStorageRepo dbStorageRepo) {
        this.dictionaryRepo = dictionaryRepo;
        this.dictionaryFieldsRepo = dictionaryFieldsRepo;
        this.dbStorageRepo = dbStorageRepo;
    }

}
