package com.tobstech.microservice.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tobstech.microservice.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
