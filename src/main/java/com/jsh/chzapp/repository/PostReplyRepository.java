package com.jsh.chzapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jsh.chzapp.model.PostReply;

public interface PostReplyRepository extends JpaRepository<PostReply, Integer>{
    @Modifying
    @Transactional
    @Query(value = "UPDATE postreply pr SET pr.valid = :valid WHERE pr.id = :id", nativeQuery = true)
    void updateValidById(@Param("id") int id, @Param("valid") boolean valid);
}
