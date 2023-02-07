package com.miu.edu.cs590.project.notification.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miu.edu.cs590.project.notification.common.ResponseEntityDTO;
import com.miu.edu.cs590.project.notification.exception.NotificationException;
import com.miu.edu.cs590.project.notification.model.KafkaPackage;
import com.miu.edu.cs590.project.notification.model.NotificationAdapter;
import com.miu.edu.cs590.project.notification.model.NotificationInfo;
import com.miu.edu.cs590.project.notification.service.EmailService;
import com.miu.edu.cs590.project.notification.service.NotificationInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class KafkaTestListener {

    @Autowired
    EmailService emailService;
    @Autowired
    NotificationInfoService notificationInfoService;
    @Value("${even.received}")
    private String eventReceivedSuccessfully;
    @Value("${even.not.received}")
    private String eventNotReceived;

    @KafkaListener(topics = "#{'${spring.kafka.template.default-topic}'}")
    public void handleMessage(String kafkaPackageSender) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        try {
            KafkaPackage kafkaPackage = objectMapper.readValue(kafkaPackageSender, KafkaPackage.class);
            ResponseEntityDTO responseEntityDTO = new ResponseEntityDTO();
            responseEntityDTO.setBooking(kafkaPackage.getBooking());
            responseEntityDTO.setRoom(kafkaPackage.getRoom());
            responseEntityDTO.setCookiesInfo(kafkaPackage.getCookiesInfo());
            NotificationInfo notificationInfo = NotificationAdapter.convertToNotificationInfoFromResponseEntityDTO(responseEntityDTO);
            log.info(eventReceivedSuccessfully);
            String informationBooking = EmailTemplate.createCustomerEmail(notificationInfo);
            emailService.sendEmailWithImage(informationBooking, notificationInfo);
            informationBooking = EmailTemplate.createOwnerEmail(notificationInfo);
            emailService.sendEmailToOwner(informationBooking, notificationInfo);

            log.info(notificationInfo.toString());
            notificationInfoService.saveNotificationInfo(notificationInfo);

        } catch (JsonProcessingException e) {
            log.error(eventNotReceived);
            log.error(e.toString() );
            throw new NotificationException(e);
        }

    }

}
