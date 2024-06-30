package com.dareen.microservices.category;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
class CategoryController {

	  private final CategoryRepository repository;
	  private final CategoryModelAssemble assembler;

  public CategoryController(CategoryRepository repository, CategoryModelAssemble assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}
  
  
// Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/categories")
  CollectionModel<EntityModel<Categories>> all() {

    List<EntityModel<Categories>> categories = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());
    return CollectionModel.of(categories, linkTo(methodOn(CategoryController.class).all()).withSelfRel());

  }
  // end::get-aggregate-root[]

  @PostMapping("/categories")
  Categories newCategory(@RequestBody Categories newCategory) {
    return repository.save(newCategory);
  }

  // Single item
  
  @GetMapping("/categories/{id}")
  EntityModel<Categories> one(@PathVariable String id) {
    
	  Categories category = repository.findById(id) //
		      .orElseThrow(() -> new CategoryNotFoundException(id));
    return assembler.toModel(category);
  }

  @PutMapping("/categories/{id}")
  Categories replaceCategory(@RequestBody Categories newCategory, @PathVariable String id) {
    
    return repository.findById(id)
      .map(category -> {
    	  category.setHtmlDescription(newCategory.getHtmlDescription());
    	  category.setImage(newCategory.getImage());
    	  category.setTextDescription(newCategory.getTextDescription());
   	  
        return repository.save(category);
      })
      .orElseGet(() -> {
        newCategory.setProductLine(id);
        return repository.save(newCategory);
      });
  }

  @DeleteMapping("/categories/{id}")
  void deleteCategory(@PathVariable String id) {
    repository.deleteById(id);
  }
}