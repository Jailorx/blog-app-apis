package com.shubham.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	private Integer id;
	
	@NotEmpty(message="Title cannot be empty")
	private String title;
	
	@NotEmpty(message="Description cannot be empty")
	@Size(min=10 message="Description must have 10 characters")
	private String description;
	
}