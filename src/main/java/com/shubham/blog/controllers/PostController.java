package com.shubham.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.blog.payloads.PostDto;
import com.shubham.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{uid}/category/{cid}/posts")
	public ResponseEntity<PostDto> createPost
	(@Valid @RequestBody PostDto postDto
			,@PathVariable Integer uid
			,@PathVariable Integer cid)
	{
		PostDto newPost=this.postService.createPost(postDto, uid, cid);
		return new ResponseEntity<>(newPost,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/posts/{id}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer id)
	{
		PostDto updatedPost=this.postService.updatePost(postDto, id);
		return new ResponseEntity<>(updatedPost,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Integer id)
	{
		this.postService.deletePost(id);
		return new ResponseEntity<>("Post deleted successfully",HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts()
	{
		List<PostDto>posts=this.postService.getAllPost();
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer id)
	{
		PostDto post=this.postService.getPostById(id);
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	
	@GetMapping("/user/{uid}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer uid)
	{
		List<PostDto> posts=this.postService.getPostByUser(uid);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/category/{cid}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer cid)
	{
		List<PostDto> posts=this.postService.getPostByCategory(cid);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
}
