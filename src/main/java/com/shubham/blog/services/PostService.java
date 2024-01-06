package com.shubham.blog.services;

import java.util.List;

import com.shubham.blog.payloads.PostDto;

public interface PostService {
	PostDto createPost(PostDto postDto,Integer uid,Integer cid);
	PostDto updatePost(PostDto postDto,Integer postId);
	void deletePost(Integer postId);
	List<PostDto> getAllPost();
	PostDto getPostById(Integer id);
	List<PostDto> getPostByCategory(Integer id);
	List<PostDto> getPostByUser(Integer id);
	List<PostDto> searchPost(String key);
	
}
