package com.jsh.chzapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsh.chzapp.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
}
