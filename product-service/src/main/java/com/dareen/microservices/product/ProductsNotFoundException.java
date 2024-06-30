package com.dareen.microservices.product;


public class ProductsNotFoundException extends RuntimeException {
	
	public ProductsNotFoundException(String id) {
	    super("Could not find product " + id);
	}

}
