package com.pviegas.orderservice.controller;

import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import com.pviegas.orderservice.model.Order;
import com.pviegas.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	// Declaração do campo rabbitTemplate
    //private final RabbitTemplate rabbitTemplate;
	private final OrderService orderService;
    
	/*public OrderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }*/
	
	public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
	/*
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Map<String, Object> order) {
        rabbitTemplate.convertAndSend("order.exchange", "order.routing.key", order);
        return ResponseEntity.ok("Order sent to RabbitMQ!");
    }*/
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        orderService.sendOrderToQueue(order);
        return ResponseEntity.ok("Order sent to queue successfully.");
    }
}
