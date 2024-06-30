package com.dareen.microservices.category;


import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;


@Primary
public interface CategoryRepository extends JpaRepository<Categories, String> {

}