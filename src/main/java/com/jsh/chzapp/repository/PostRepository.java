package com.jsh.chzapp.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jsh.chzapp.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{
    @Query(value = "SELECT * FROM post WHERE valid = true", nativeQuery = true)
    Page<Post> findPostALLByValid(Pageable pageable);
    
    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.valid = :valid WHERE p.id = :id")
    void updateValidById(@Param("id") int id, @Param("valid") boolean valid);
}
