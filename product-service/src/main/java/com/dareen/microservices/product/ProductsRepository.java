package com.dareen.microservices.product;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;





public interface ProductsRepository extends JpaRepository<Products, String> {


}