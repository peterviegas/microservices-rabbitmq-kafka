package com.pviegas.orderservice.service;

import com.pviegas.orderservice.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.pviegas.orderservice.config.RabbitConfig;

@Service
public class OrderService {
	
	private final RabbitTemplate rabbitTemplate;
    /*private static final String EXCHANGE_NAME = "order-exchange";
    private static final String ROUTING_KEY = "order.routing";*/
    
    public OrderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    /*public void sendOrderToQueue(Order order) {
        try {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, order);
            System.out.println("Order sent to RabbitMQ: " + order);
        } catch (Exception e) {
            System.err.println("Failed to send order: " + e.getMessage());
        }
    }*/
    
    public void sendOrderToQueue(Order order) {
        try {
            // Use os valores do RabbitConfig para garantir consistência
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.ROUTING_KEY, order);
            System.out.println("Order sent to RabbitMQ: " + order);
        } catch (Exception e) {
            System.err.println("Failed to send order: " + e.getMessage());
        }
    }

}
