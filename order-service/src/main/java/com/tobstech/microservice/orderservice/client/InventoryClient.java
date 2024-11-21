package com.tobstech.microservice.orderservice.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import groovy.util.logging.Slf4j;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

//@FeignClient(value = "inventory", url = "${inventory.url}") //using openfeign
@Slf4j
public interface InventoryClient {
	
	Logger log = LoggerFactory.getLogger(InventoryClient.class);
	
	//@GetMapping("/api/inventory") //using openfeign
	@GetExchange("/api/inventory") //restclient
	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
	@Retry(name = "inventory")
	boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
	
	//fallback method
	default boolean fallbackMethod(String code, Integer quantity, Throwable throwable) {
		log.info("Cannot get inventory for skucode {}, failure reason: {}", code, throwable.getMessage());
		return false;
	}
	

}
