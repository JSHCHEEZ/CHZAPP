package com.jsh.chzapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
	public void createReply(PostReply reply, int postId) {
		Post post = postRepository.findById(postId)
								.orElseThrow(()-> {return new IllegalArgumentException("댓글쓰기 실패: id를 찾을 수 없습니다 ");});
		
		reply.setPost(post);
		
		postReplyRepository.save(reply);
	}
	
	@Transactional
	public void createRereply(PostReply rereply, int replyId) {
		PostReply postReply = postReplyRepository.findById(replyId)
								.orElseThrow(()-> {return new IllegalArgumentException("대댓글쓰기 실패: id를 찾을 수 없습니다 ");});
		
		rereply.setPostReply(postReply);
		
		postReplyRepository.save(rereply);
	}
	
	@Transactional
	public void deleteReply(int id){
		postReplyRepository.updateValidById(id, false);
	}
	
	@Transactional
	public void updateReply(int id, PostReply requestPostReply) {
		PostReply postReply = postReplyRepository.findById(id)
									.orElseThrow(()-> {return new IllegalArgumentException("댓글수정 실패: id를 찾을 수 없습니다 ");});
		
		postReply.setContent(requestPostReply.getContent());
	}
	
}
