package com.jsh.chzapp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsh.chzapp.dto.ResponseDTO;
import com.jsh.chzapp.model.Post;
import com.jsh.chzapp.service.PostService;

@RestController
public class PostApiController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/api/post")
	public ResponseDTO<Integer> save(@RequestBody Post post) { //, @AuthenticationPrincipal PrincipalDetail principal
		postService.createPost(post);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
}
