package com.shubham.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.blog.entities.Category;
import com.shubham.blog.entities.Post;
import com.shubham.blog.entities.User;
import com.shubham.blog.exceptions.ResourceNotFoundException;
import com.shubham.blog.payloads.PostDto;
import com.shubham.blog.repositories.CategoryRespository;
import com.shubham.blog.repositories.PostRepository;
import com.shubham.blog.repositories.UserRepository;
import com.shubham.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRespository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer uid,Integer cid) {
		User user=this.userRepository.findById(uid)
				.orElseThrow(()->new ResourceNotFoundException("User","id",uid));
		Category category=this.categoryRepository.findById(cid)
				.orElseThrow(()->new ResourceNotFoundException("Category","id",cid));
		
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImgName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		
		Post savedPost=this.postRepository.save(post);
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepository.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImgName(postDto.getImgName());
		
		Post updatedPost=this.postRepository.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepository.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
		
		this.postRepository.delete(post);
		
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> posts=this.postRepository.findAll();
		List<PostDto> dto=posts.stream().map(post->this.modelMapper.map(post,PostDto.class))
				.collect(Collectors.toList());
;		return dto;
	}

	@Override
	public PostDto getPostById(Integer id) {
		Post post=this.postRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Post","id",id));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer id) {		
		Category category=this.categoryRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Category","id",id));
		
		List<Post> posts=this.postRepository.findByCategory(category);
		
		List<PostDto> dto=posts.stream()
		.map(post->this.modelMapper.map(post, PostDto.class))
		.collect(Collectors.toList());
		
		return dto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer id) {
		User user=this.userRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User","id",id));
		
		List<Post> posts=this.postRepository.findByUser(user);
		
		List<PostDto> dto=posts.stream()
				.map(post->this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		return dto;
	}

	@Override
	public List<PostDto> searchPost(String key) {
		
		return null;
	}

}
