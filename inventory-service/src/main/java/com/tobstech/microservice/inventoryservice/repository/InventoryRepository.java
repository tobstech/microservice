package com.tobstech.microservice.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tobstech.microservice.inventoryservice.model.Inventory;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);

}
