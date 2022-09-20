package com.example.udbtest.service;

import com.example.udbtest.model.DbRow;
import com.example.udbtest.model.DbStorage;
import com.example.udbtest.model.Dictionary;
import com.example.udbtest.model.DictionaryFields;
import com.example.udbtest.repository.DbRowRepo;
import com.example.udbtest.repository.DbStorageRepo;
import com.example.udbtest.repository.DictionaryFieldsRepo;
import com.example.udbtest.repository.DictionaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DbStorageService {

    private DictionaryFieldsRepo dictionaryFieldsRepo;
    private DictionaryRepo dictionaryRepo;
    private DbStorageRepo dbStorageRepo;
    private DbRowRepo dbRowRepo;


    public Boolean addEntryField (String dictionaryName, String fieldName, String value){
        Boolean result = false;
        Dictionary dictionary = dictionaryRepo.findByName(dictionaryName);
        DictionaryFields dictionaryFields = dictionaryFieldsRepo.findByNameAndDictionary(fieldName, dictionary);
        DbStorage dbStorage = new DbStorage();
        dbStorage.setDictionaryField(dictionaryFields);
        dbStorage.setValue(value);

        dbStorageRepo.save(dbStorage);

        return result;
    }

    public Boolean addEntryMap(String dictionaryName, Map<String, String> valueMap){
        Boolean result = false;
        List<DbStorage> dbStorageList = new ArrayList<>();
        Dictionary dictionary = dictionaryRepo.findByName(dictionaryName);
        DbRow dbRow = new DbRow();
        dbRowRepo.save(dbRow);

        for(Map.Entry entry : valueMap.entrySet()){
            DictionaryFields dictionaryFields = dictionaryFieldsRepo.findByNameAndDictionary(entry.getKey().toString(), dictionary);
            DbStorage dbStorage = new DbStorage();
            dbStorage.setDictionaryField(dictionaryFields);
            dbStorage.setValue(entry.getValue().toString());
            dbStorage.setDbRow(dbRow);
            dbStorageList.add(dbStorage);
        }

        dbStorageRepo.saveAllAndFlush(dbStorageList);

        return result;
    }

    public Map<Long, Map<String, String>> getDbStorageMap (String dictionaryName, String fieldName, String fieldValue){

        List<DbStorage> dbStorageResultlist = new ArrayList<>();

        Dictionary dictionary = dictionaryRepo.findByName(dictionaryName);

        DictionaryFields dictionaryFields = dictionaryFieldsRepo.findByNameAndDictionary(fieldName, dictionary);
        List<DbStorage> tempDbStorageList = dbStorageRepo.findDbStoragesByDictionaryFieldAndValue(dictionaryFields, fieldValue);

        List<Long> tempList = new ArrayList<>();
        for(DbStorage dbStorage : tempDbStorageList){
            tempList.add(dbStorage.getId());
        }

        List<DbRow> dbRowList = dbRowRepo.findDbRowsByIdIn(tempList);
        dbStorageResultlist.addAll(dbStorageRepo.findDbStoragesByDbRowIn(dbRowList));

        return getRowList(dbStorageResultlist);
    }


    public Boolean updateRow(Long rowNumber, Map<String, String> valueMap){

        if("".equals(rowNumber) || rowNumber==null || valueMap==null){
            return false;
        }else {
            DbRow dbRow = dbRowRepo.findDbRowById(rowNumber);
            List<DbStorage> storageList = dbStorageRepo.findDbStoragesByDbRow(dbRow);
            for (DbStorage dbStorage : storageList) {
                String field = dbStorage.getDictionaryField().getName();
                dbStorage.setValue(valueMap.get(field));
            }
            dbStorageRepo.saveAllAndFlush(storageList);
            return true;
        }
    }

    public Boolean deleteRow(Long rowNumber){
        DbRow dbRow = dbRowRepo.findDbRowById(rowNumber);
        List<DbStorage> storageList = dbStorageRepo.findDbStoragesByDbRow(dbRow);
        if(rowNumber == null && storageList.size()==0){
            return false;
        }else {
            dbStorageRepo.deleteAll(storageList);
            dbStorageRepo.flush();
            return true;
        }
    }

    public Map<Long, Map<String, String>> getDbStorageMap (String dictionaryName, Map<String, String> filterMap){

        List<DbStorage> dbStorageResultlist = new ArrayList<>();
        List<Long> resultRowIdList = new ArrayList<>();


        for (Map.Entry entry : filterMap.entrySet()) {
            String fieldName = entry.getKey().toString();
            String fieldValue = entry.getValue().toString();
            Dictionary dictionary = dictionaryRepo.findByName(dictionaryName);

            DictionaryFields dictionaryFields = dictionaryFieldsRepo.findByNameAndDictionary(fieldName, dictionary);
            List<DbStorage> tempDbStorageList = dbStorageRepo.findDbStoragesByDictionaryFieldAndValue(dictionaryFields, fieldValue);
            List<Long> tempList = new ArrayList<>();
            for (DbStorage dbStorage : tempDbStorageList) {
                tempList.add(dbStorage.getDbRow().getId());
            }

            if(resultRowIdList.size()==0){
                resultRowIdList.addAll(tempList);
            }else{
                resultRowIdList.retainAll(tempList);
            }
        }

        List<DbRow> dbRowList = dbRowRepo.findDbRowsByIdIn(resultRowIdList);
        dbStorageResultlist.addAll(dbStorageRepo.findDbStoragesByDbRowIn(dbRowList));

        return getRowList(dbStorageResultlist);
    }


    private Map<Long, Map<String, String>> getRowList (List<DbStorage> dbStorageList){
        Map<Long, Map<String, String>> resultMap = new HashMap<>();

        for(DbStorage dbStorage : dbStorageList){
            Long key = dbStorage.getDbRow().getId();
            if(resultMap.containsKey(key)){
                resultMap.get(key).put(dbStorage.getDictionaryField().getName(), dbStorage.getValue());
            }else{
                Map<String, String> innerMap = new HashMap<String, String>();
                innerMap.put(dbStorage.getDictionaryField().getName(), dbStorage.getValue());
                resultMap.put(key, innerMap);
            }
        }
        return resultMap;
    }


    @Autowired
    public DbStorageService(DictionaryFieldsRepo dictionaryFieldsRepo, DictionaryRepo dictionaryRepo, DbStorageRepo dbStorageRepo, DbRowRepo dbRowRepo) {
        this.dictionaryFieldsRepo = dictionaryFieldsRepo;
        this.dictionaryRepo = dictionaryRepo;
        this.dbStorageRepo = dbStorageRepo;
        this.dbRowRepo = dbRowRepo;
    }
}
