package com.jsh.chzapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsh.chzapp.model.Post;
import com.jsh.chzapp.model.PostReply;
import com.jsh.chzapp.repository.PostReplyRepository;
import com.jsh.chzapp.repository.PostRepository;

@Service
public class PostReplyService {
	
	@Autowired
	private PostReplyRepository postReplyRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public void createReply(PostReply reply, int postId) {
		Post post = postRepository.findById(postId)
								.orElseThrow(()-> {return new IllegalArgumentException("댓글쓰기 실패: 사용자의 id를 찾을 수 없습니다 ");});
		
		reply.setPost(post);
		
		postReplyRepository.save(reply);
	}
	
}
