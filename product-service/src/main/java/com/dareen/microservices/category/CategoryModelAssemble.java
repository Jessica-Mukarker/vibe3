package com.dareen.microservices.category;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;



	@Component
	public class CategoryModelAssemble implements RepresentationModelAssembler<Categories, EntityModel<Categories>> {

	  @Override
	  public EntityModel<Categories> toModel(Categories category) {


		    return EntityModel.of(category, //
		        linkTo(methodOn(CategoryController.class).one(category.getProductLine())).withSelfRel(),
		        linkTo(methodOn(CategoryController.class).all()).withRel("categories"));
		  }
		}