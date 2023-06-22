package com.springbootbackend.springbootbackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootbackend.springbootbackend.Email.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String emailRequestsTopic;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmailRequest(EmailRequest emailRequest) throws JsonProcessingException {
        // Serialize the email request as a JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(emailRequest);

        // Send the email request to the Kafka topic
        kafkaTemplate.send(emailRequestsTopic, requestJson);
        System.out.println("In producer service");
    }
}
