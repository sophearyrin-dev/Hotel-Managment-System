package com.sa.sample.project.kafka;

import com.sa.sample.project.kafka.KafkaPackage;

public interface KafkaSenderService {

    void receiveEvent(KafkaPackage kafkaPackage);

}
