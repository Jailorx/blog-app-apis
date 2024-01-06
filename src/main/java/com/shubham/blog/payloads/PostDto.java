package com.shubham.blog.payloads;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	@NotEmpty
	private String title;
	@Size(min=10)
	private String content;
	private String imgName;
	private Date addedDate;
	private UserDto user;
	private CategoryDto category;
	
}
