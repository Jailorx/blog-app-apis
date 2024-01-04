package com.shubham.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.shubham.blog.entities.User;
import com.shubham.blog.exceptions.ResourceNotFoundException;
import com.shubham.blog.payloads.UserDto;
import com.shubham.blog.repositories.UserRepository;

public class UserService implements com.shubham.blog.services.UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepository.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		User user=this.userRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User","id",id));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser=this.userRepository.save(user);
		UserDto dto=this.userToDto(updatedUser);
		
		return dto;
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user=this.userRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User","id",id));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepository.findAll();
		
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer id) {
		User user=this.userRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User","id",id));
		
		this.userRepository.delete(user);

	}
	
	private User dtoToUser(UserDto userDto)
	{
		User user=new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
		return user;
	}
	private UserDto userToDto(User user)
	{
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		userDto.setPassword(user.getPassword());
		
		return userDto;
	}


}
