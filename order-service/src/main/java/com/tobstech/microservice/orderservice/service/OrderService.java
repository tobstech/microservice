package com.tobstech.microservice.orderservice.service;

import java.util.UUID;

import org.bouncycastle.crypto.ec.ECNewPublicKeyTransform;
import org.springframework.stereotype.Service;

import com.tobstech.microservice.orderservice.client.InventoryClient;
import com.tobstech.microservice.orderservice.dto.OrderRequest;
import com.tobstech.microservice.orderservice.model.Order;
import com.tobstech.microservice.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.experimental.var;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	private final InventoryClient inventoryClient;
	
	
	public void placeOrder(OrderRequest orderRequest) {
		//check if quantity is in stock - use openfeign to call the inventory service 
		boolean isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
		if(isProductInStock) {
			//map order request to order object
			Order order = new Order();
			order.setOrderNumber(UUID.randomUUID().toString());
			order.setPrice(orderRequest.price());
			order.setQuantity(orderRequest.quantity());
			order.setSkuCode(orderRequest.skuCode());
			//save order
			orderRepository.save(order);
		}else {
			throw new RuntimeException("Product with SkuCode " + orderRequest.skuCode() + " is not in stock.");
		}		
	}
	
	
	
	
}
