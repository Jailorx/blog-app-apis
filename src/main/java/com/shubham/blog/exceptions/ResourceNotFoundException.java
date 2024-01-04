package com.shubham.blog.exceptions;

public class ResourceNotFoundException  extends RuntimeException{
	String resource;
	String field;
	long value;
	
	public ResourceNotFoundException(String resource,String field,long value)
	{
		super(resource+" not found with "+field+":"+value);
	}

}
