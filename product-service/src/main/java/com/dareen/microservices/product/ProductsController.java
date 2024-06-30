package com.dareen.microservices.product;


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
class ProductsController {

	  private final ProductsRepository repository;
	  private final ProductsModelAssemble assembler;

  public ProductsController(ProductsRepository repository, ProductsModelAssemble assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}


// Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/products")
  CollectionModel<EntityModel<Products>> all() {

    List<EntityModel<Products>> products = repository.findAll().stream() //
    		.map(assembler::toModel) //
            .collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductsController.class).all()).withSelfRel());
  }
  // end::get-aggregate-root[]

  @PostMapping("/products")
  Products newProduct(@RequestBody Products newProduct) {
    return repository.save(newProduct);
  }

  // Single item

  @GetMapping("/products/{id}")
  EntityModel<Products> one(@PathVariable String id) {

	  Products products = repository.findById(id) //
		      .orElseThrow(() -> new ProductsNotFoundException(id));
    return assembler.toModel(products);
  }

  @PutMapping("/products/{id}")
  Products replaceProducts(@RequestBody Products newProduct, @PathVariable String id) {

    return repository.findById(id)
      .map(product -> {
    	  
    	  product.setProductCode(newProduct.getProductCode());
    	  product.setProductName(newProduct.getProductName());
    	  product.setProductLine(newProduct.getProductLine());
    	  product.setProductScale(newProduct.getProductScale());
    	  product.setProductVendor(newProduct.getProductVendor());
    	  product.setProductDescription(newProduct.getProductDescription());
    	  product.setQuantityInStock(newProduct.getQuantityInStock());
    	  product.setBuyPrice(newProduct.getBuyPrice());
    	  product.setMSRP(newProduct.getMSRP());
    	  	  
        return repository.save(product);
      })
      .orElseGet(() -> {
        newProduct.setProductCode(id);
        return repository.save(newProduct);
      });
  }
  

  @DeleteMapping("/products/{id}")
  void deleteProduct(@PathVariable String id) {
    repository.deleteById(id);
  }
} 
 