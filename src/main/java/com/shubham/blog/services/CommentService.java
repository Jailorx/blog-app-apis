package com.shubham.blog.services;

import com.shubham.blog.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto,Integer id);
	void deleteComment(Integer id);
}
