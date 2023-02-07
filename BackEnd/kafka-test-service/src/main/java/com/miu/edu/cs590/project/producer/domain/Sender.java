package com.miu.edu.cs590.project.producer.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String message){
        kafkaTemplate.send(topic, message);
    }

}
