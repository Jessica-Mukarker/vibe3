package com.dareen.microservices.category;

public class CategoryNotFoundException extends RuntimeException {
	
	public CategoryNotFoundException(String id) {
	    super("Could not find Category " + id);
	}

}
