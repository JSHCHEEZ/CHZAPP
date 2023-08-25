package com.jsh.chzapp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseDTO<Integer> saveReply(@PathVariable int postId,@RequestBody PostReply reply){ //, @AuthenticationPrincipal PrincipalDetail principal
		postReplyService.createReply(reply, postId);
				
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PostMapping("/api/rereply/{replyId}")
	public ResponseDTO<Integer> saveRereply(@PathVariable int replyId,@RequestBody PostReply rereply){ //, @AuthenticationPrincipal PrincipalDetail principal
		postReplyService.createRereply(rereply, replyId);
				
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/api/reply/delete/{id}")
	public ResponseDTO<Integer> deleteReply(@PathVariable int id){
		postReplyService.deleteReply(id);
				
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/api/reply/{id}")
	public ResponseDTO<Integer> updateReply(@PathVariable int id, @RequestBody PostReply postReply){
		postReplyService.updateReply(id, postReply);
		
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}

}
