package com.jsh.chzapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsh.chzapp.model.Post;
import com.jsh.chzapp.repository.PostRepository;


@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Transactional(readOnly = true)
	public Page<Post> selectAll(Pageable pageable){
		return postRepository.findAll(pageable);
	}
	
	@Transactional
	public void createPost(Post post) {
		postRepository.save(post);
	}
	
}
