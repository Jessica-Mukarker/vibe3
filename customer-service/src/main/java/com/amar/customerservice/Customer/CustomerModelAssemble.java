package com.amar.customerservice.Customer;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


		

	@Component
	public class CustomerModelAssemble implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {

	  @Override
	  public EntityModel<Customer> toModel(Customer customer) {

	    return EntityModel.of(customer, //
	        linkTo(methodOn(CustomerController.class).one(customer.getid())).withSelfRel(),
	        linkTo(methodOn(CustomerController.class).all()).withRel("customers"));
	  }
	}