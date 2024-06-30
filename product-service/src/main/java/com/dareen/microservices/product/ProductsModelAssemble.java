package com.dareen.microservices.product;





	import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

	import org.springframework.hateoas.EntityModel;
	import org.springframework.hateoas.server.RepresentationModelAssembler;
	import org.springframework.stereotype.Component;


	@Component
	public class ProductsModelAssemble implements RepresentationModelAssembler<Products, EntityModel<Products>> {

	  @Override
	  public EntityModel<Products> toModel(Products product) {

	    return EntityModel.of(product, //
	        linkTo(methodOn(ProductsController.class).one(product.getProductCode())).withSelfRel(),
	        linkTo(methodOn(ProductsController.class).all()).withRel("products"));
	  }
	}