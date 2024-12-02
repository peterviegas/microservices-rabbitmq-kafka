package com.pviegas.notificationservice.producer;

import com.pviegas.notificationservice.model.Notification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

	private static final String TOPIC_NAME = "notification-topic";
    private final KafkaTemplate<String, String> kafkaTemplate; // Alterado para String, pois estamos enviando um JSON string.

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendToKafka(String message) { // Agora espera uma String
        kafkaTemplate.send(TOPIC_NAME, message); // Envia a string JSON
        System.out.println("Sent to Kafka: " + message); // Exibe a string enviada
    }
}
