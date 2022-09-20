package com.example.udbtest.repository;

import com.example.udbtest.model.DbRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DbRowRepo extends JpaRepository<DbRow, Long> {
    List<DbRow> findDbRowsByIdIn(List<Long> idList);
    DbRow findDbRowById(Long id);
}
