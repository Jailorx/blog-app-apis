package com.shubham.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.blog.entities.Comment;
import com.shubham.blog.entities.Post;
import com.shubham.blog.entities.User;
import com.shubham.blog.exceptions.ResourceNotFoundException;
import com.shubham.blog.payloads.CommentDto;
import com.shubham.blog.repositories.CommentRepository;
import com.shubham.blog.repositories.PostRepository;
import com.shubham.blog.repositories.UserRepository;
import com.shubham.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepository postRespository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer id) {
		Post post=this.postRespository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Post","id",id));
		User user=this.userRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User","id",id));
		
		Comment comment=this.modelMapper.map(commentDto,Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment newComment=this.commentRepository.save(comment);
		return this.modelMapper.map(newComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer id) {
		// TODO Auto-generated method stub
		Comment comment=this.commentRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Comment","id",id));
		
		this.commentRepository.delete(comment);
	}

}
