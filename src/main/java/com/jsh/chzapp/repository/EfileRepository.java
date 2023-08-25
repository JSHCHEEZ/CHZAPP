package com.jsh.chzapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jsh.chzapp.model.Efile;

public interface EfileRepository extends JpaRepository<Efile, Integer>{
    @Modifying
    @Transactional
    @Query("UPDATE Efile e SET e.valid = :valid WHERE e.id = :id")
    void updateValidById(@Param("id") int id, @Param("valid") boolean valid);
}
