package com.tobstech.microservice.productservice.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tobstech.microservice.productservice.dto.ProductRequest;
import com.tobstech.microservice.productservice.dto.ProductResponse;
import com.tobstech.microservice.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService; 
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {		
		return productService.createProduct(productRequest);
	}
	
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProduct() {		
		return productService.getAllProduct();
	}

}
