package com.springbootbackend.springbootbackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootbackend.springbootbackend.Email.EmailRequest;
import com.springbootbackend.springbootbackend.email_service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerService {

    @Autowired
    private EmailSenderService emailService;

    @KafkaListener(id = "myGroup",topics = "${spring.kafka.topic.name}")
    public void processEmailRequest(String requestJson) throws JsonProcessingException {

        System.out.println("In ConsumerService");

        // Deserialize the email request data from the JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        EmailRequest emailRequest = objectMapper.readValue(requestJson, EmailRequest.class);

        // Extract the necessary information from the email request
        String to = emailRequest.getTo();
        String name = emailRequest.getName();

        //Mail Sending Method
        emailService.sendInBlue(to,name);
    }
}


