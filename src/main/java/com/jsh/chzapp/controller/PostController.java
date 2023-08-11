package com.jsh.chzapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jsh.chzapp.service.PostService;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;

	@GetMapping("/post")
	public String postAll(Model model, @PageableDefault(size=5, sort = "id", direction = Direction.DESC) Pageable pageable) {
		model.addAttribute("posts", postService.selectAll(pageable));
		return "post/post";
	}
	
	@GetMapping("/post/view/{id}")
	public String postDetail(@PathVariable int id, Model model) {
		model.addAttribute("post", postService.selectById(id));
		return "post/postView";
	}
	
	@GetMapping({"/post/form", "/post/form/{id}"})
	public String postModify() {
		return "post/postInfo";
	}
	
}
