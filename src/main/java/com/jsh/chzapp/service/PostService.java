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
		return postRepository.findPostALLByValid(pageable);
	}
	
	@Transactional(readOnly = true)
	public Post selectById(int id){
		return postRepository.findById(id)
				.orElseThrow(()-> {return new IllegalArgumentException("글 상세보기 실패: 인덱스를 찾을 수 없습니다. id : " + id);});
	}
	
	@Transactional
	public Post createPost(Post post) {
		return postRepository.save(post);
	}
	
	@Transactional
    public void updatePostValid(int id, boolean valid) {
        postRepository.updateValidById(id, valid);
    }

	
}
