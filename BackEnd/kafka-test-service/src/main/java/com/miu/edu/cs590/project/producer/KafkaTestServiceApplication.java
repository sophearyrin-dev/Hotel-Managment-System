package com.miu.edu.cs590.project.producer;

import com.miu.edu.cs590.project.producer.service.KafkaSenderService;
import com.miu.edu.cs590.project.producer.service.KafkaSenderServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaTestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaTestServiceApplication.class, args);
    }

    @Bean
    public KafkaSenderService kafkaSenderService() {
        return new KafkaSenderServiceImpl();
    }

}
