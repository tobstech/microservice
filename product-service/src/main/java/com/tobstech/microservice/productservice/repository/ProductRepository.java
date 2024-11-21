package com.tobstech.microservice.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tobstech.microservice.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
