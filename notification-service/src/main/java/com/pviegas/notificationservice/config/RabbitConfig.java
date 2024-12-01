package com.pviegas.notificationservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Configuration to consume the order queue 
@Configuration
public class RabbitConfig {
	
	public static final String QUEUE_NAME = "order.queue";

    @Bean
    public Queue orderQueue() {
        return new Queue(QUEUE_NAME, true);
    }
}