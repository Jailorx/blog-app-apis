package com.shubham.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.blog.entities.Category;
import com.shubham.blog.exceptions.ResourceNotFoundException;
import com.shubham.blog.payloads.CategoryDto;
import com.shubham.blog.repositories.CategoryRespository;
import com.shubham.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRespository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category=this.dtoToCategory(categoryDto);
		Category savedCategory=this.categoryRepository.save(category);
		return this.CategoryToDto(savedCategory);
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		Category category=this.categoryRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Category","id",id));
		
		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());
		
		Category updatedCategory=this.categoryRepository.save(category);
		return this.CategoryToDto(updatedCategory);
		
	}

	@Override
	public void deleteCategory(Integer id) {
		Category category=this.categoryRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Category","id",id));
		

		this.categoryRepository.delete(category);

	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories=this.categoryRepository.findAll();
		List<CategoryDto> list=categories.stream().map(category->this.CategoryToDto(category)).collect(Collectors.toList());
		return list;
	}

	@Override
	public CategoryDto getCategory(Integer id) {
		Category category=this.categoryRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Category","id",id));
		
		return this.CategoryToDto(category);
		
	}
	
	private Category dtoToCategory(CategoryDto categoryDto)
	{
		Category category=this.modelMapper.map(categoryDto, Category.class);
		return category;
	}
	
	private CategoryDto CategoryToDto(Category category)
	{
		CategoryDto dto=this.modelMapper.map(category,CategoryDto.class);
		return dto;
	}

}
