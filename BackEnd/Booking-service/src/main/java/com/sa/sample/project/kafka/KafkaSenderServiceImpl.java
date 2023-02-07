package com.sa.sample.project.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaSenderServiceImpl implements KafkaSenderService {

    @Autowired
    Sender sender;
    @Value("${topic.value}")
    private String topic;

    @Override
    public void receiveEvent(KafkaPackage kafkaPackage) {

        log.info("Object: " + kafkaPackage);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String createInformationTestAsString = null;
        try {
            log.info("Before Sending Topic");
            createInformationTestAsString = objectMapper.writeValueAsString(kafkaPackage);
            sender.send(topic, createInformationTestAsString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
