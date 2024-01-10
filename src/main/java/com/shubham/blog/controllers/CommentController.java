package com.shubham.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.shubham.blog.payloads.CommentDto;
import com.shubham.blog.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/posts/{id}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,@PathVariable Integer id)
	{
		CommentDto dto=this.commentService.createComment(comment, id);
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/comments/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable Integer id)
	{
		this.commentService.deleteComment(id);
		
		return new ResponseEntity<>("Comment Deleted Succesfully",HttpStatus.OK);
	}

}
