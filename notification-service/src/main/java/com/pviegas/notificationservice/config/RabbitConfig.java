package com.pviegas.notificationservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import com.pviegas.notificationservice.consumer.RabbitConsumer;
import org.springframework.amqp.support.converter.MessageConverter;


//Configuration to consume the order queue 
@Configuration
public class RabbitConfig {
	
	public static final String QUEUE_NAME = "order.queue";

	@Bean
    public Queue orderQueue() {
        return new Queue(QUEUE_NAME, true);
    }
    
    // Configuração do MessageListenerContainer
    /*@Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory, MessageListener messageListener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(messageListener);
        return container;
    }*/
    
   
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(
            ConnectionFactory connectionFactory,
            MessageListenerAdapter messageListenerAdapter) { // Especifica MessageListenerAdapter
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(messageListenerAdapter); // Passa o listener correto
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(RabbitConsumer rabbitConsumer, MessageConverter messageConverter) {
        return new MessageListenerAdapter(rabbitConsumer, messageConverter);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}