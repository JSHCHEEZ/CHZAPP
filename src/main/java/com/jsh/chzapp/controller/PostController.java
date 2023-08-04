package com.jsh.chzapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

	@GetMapping("/post")
	public String postAll() {
		return "post/post";
	}
	
	@GetMapping("/post/detail/{id}")
	public String postDetail() {
		return "post/postForm";
	}
	
	@GetMapping({"/post/form", "/post/form/{id}"})
	public String postModify() {
		return "post/postInfo";
	}
	
}
