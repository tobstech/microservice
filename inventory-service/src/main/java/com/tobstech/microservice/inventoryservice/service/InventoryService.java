package com.tobstech.microservice.inventoryservice.service;

import org.springframework.stereotype.Service;

import com.tobstech.microservice.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepository inventoryRepository;
	
	
	public Boolean isInStock(String skuCode, Integer quantity) {
		//find an inventory for a given skuCode where quantity >= 0
		return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
	}
	
}
