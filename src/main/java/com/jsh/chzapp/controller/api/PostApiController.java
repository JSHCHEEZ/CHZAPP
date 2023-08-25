package com.jsh.chzapp.controller.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsh.chzapp.dto.ResponseDTO;
import com.jsh.chzapp.model.Post;
import com.jsh.chzapp.repository.PostRepository;
import com.jsh.chzapp.service.EfileService;
import com.jsh.chzapp.service.PostService;

@RestController
public class PostApiController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private EfileService efileService;
	
	@PostMapping("/api/post")
	public ResponseDTO<Post> uploadContent(@RequestBody Post post){
		Post newPost = postService.createPost(post);
			
		return new ResponseDTO<Post>(HttpStatus.OK.value(), newPost);
	}

	@PostMapping("/api/fileUpload")
	public ResponseDTO<Integer> uploadChunk(
            @RequestParam("fileChunk") MultipartFile fileChunk,
            @RequestParam("totalChunks") int totalChunks,
            @RequestParam("currentChunk") int currentChunk,
            @RequestParam("file") MultipartFile file,
            @RequestParam("postId") int postId) {

        try {
        	Post newPost = postService.selectById(postId);
        	
        	efileService.uploadChunk(fileChunk, totalChunks, currentChunk, file, newPost);
        	
            return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
        } catch (IOException e) {
            return new ResponseDTO<Integer>(HttpStatus.NOT_FOUND.value(), 0);
        }
    }
	
	@PutMapping("/api/post/{id}")
	public ResponseDTO<Post> update(@PathVariable int id, @RequestBody Post post){
		Post editPost = postService.updatePost(id, post);
		
		return new ResponseDTO<Post>(HttpStatus.OK.value(), editPost);
	}
	
	@PutMapping("/api/post/delete/{id}")
	public ResponseDTO<Integer> delete(@PathVariable int id){
		postService.updatePostValid(id);
		
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	

}
