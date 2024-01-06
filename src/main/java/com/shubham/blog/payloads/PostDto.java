package com.shubham.blog.payloads;

import java.util.Date;

import com.shubham.blog.entities.Category;
import com.shubham.blog.entities.User;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	private String title;
	private String content;
	private String imgName;
	private Date addedDate;
	private UserDto user;
	private CategoryDto category;
	
}
