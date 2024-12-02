package com.pviegas.notificationservice.consumer;

import com.pviegas.notificationservice.model.Notification;
import com.pviegas.notificationservice.producer.KafkaProducer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;



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

@Component
public class RabbitConsumer {
	
	private final KafkaProducer kafkaProducer;

    public RabbitConsumer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
	
	public KafkaProducer kafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
	    return new KafkaProducer(kafkaTemplate);
	}
	
	@RabbitListener(queues = "order.queue")
    public void receiveOrder(Notification notification) {
		
		if (notification == null) {
            System.err.println("Received null Notification object from RabbitMQ.");
            return;
        }
		
        System.out.println("Received from RabbitMQ: " + notification);

        try {
            // Converter o objeto Notification para JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String message = objectMapper.writeValueAsString(notification);
            
            // Enviar a mensagem JSON ao Kafka
            kafkaProducer.sendToKafka(message);
            System.out.println("Sent to Kafka: " + message);
        } catch (JsonProcessingException e) {
            System.err.println("Error converting Notification to JSON: " + e.getMessage());
        } catch (Exception e) {
            // Logar outros erros inesperados
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

}
