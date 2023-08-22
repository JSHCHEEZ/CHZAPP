package com.jsh.chzapp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsh.chzapp.dto.ResponseDTO;
import com.jsh.chzapp.model.PostReply;
import com.jsh.chzapp.service.PostReplyService;

@RestController
public class PostReplyApiController {

	@Autowired
	private PostReplyService postReplyService;
	
	@PostMapping("/api/reply/{postId}")
	public ResponseDTO<Integer> save(@PathVariable int postId,@RequestBody PostReply reply){ //, @AuthenticationPrincipal PrincipalDetail principal
		postReplyService.createReply(reply, postId);
				
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
//	@PutMapping("/api/post/delete/{id}")
//	public ResponseDTO<Integer> delete(@PathVariable int id){
//		postService.updatePostValid(id, false);
//		
//		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
//	}
//	

}
