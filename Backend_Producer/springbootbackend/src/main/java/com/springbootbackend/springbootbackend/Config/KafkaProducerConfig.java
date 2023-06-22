package com.springbootbackend.springbootbackend.Config;

import com.springbootbackend.springbootbackend.StudentDetails.StudentDetails;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

public class KafkaProducerConfig {
    /** inject the value of topic name from application.properties*/
    @Value("${spring.kafka.topic.name}")
    private String kafkaService;

    /**
     * This method is used for creating a map of configuration properties for Kafka producer.
     */
    public Map<String,Object> producerConfig(){
        HashMap<String,Object> config=new HashMap<>();
        /** set the bootstrap server and serializer classes */
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaService);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return config;
    }

    /**
     * This method is used for creating a bean of ProducerFactory for sending messages of type String and StudentDetails.
     */
    @Bean
    public ProducerFactory<String,StudentDetails> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    /**
     * This method is used for creating a bean of KafkaTemplate for sending messages to a topic.
     */
    @Bean
    public KafkaTemplate<String, StudentDetails> kafkaTemplate(ProducerFactory<String,StudentDetails> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
