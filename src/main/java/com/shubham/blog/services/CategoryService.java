package com.shubham.blog.services;

import java.util.List;

import com.shubham.blog.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto,Integer id);
	void deleteCategory(Integer id);
	List<CategoryDto> getAllCategory();
	CategoryDto getCategory(Integer id);
}
