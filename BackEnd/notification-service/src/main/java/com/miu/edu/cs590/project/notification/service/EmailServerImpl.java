package com.miu.edu.cs590.project.notification.service;

import com.miu.edu.cs590.project.notification.model.NotificationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServerImpl implements EmailService {

    @Autowired
    JavaMailSender javaMailSender;
    @Value("${email.address}")
    private String emailSender;

    @Value("${email.customer.subject}")
    private String emailCustomerSubject;

    @Value("${email.owner.subject}")
    private String emailOwnerSubject;
    @Value("${email.owner}")
    private String emailOwner;

    @Override
    public void sendEmailWithImage(String emailInfo, NotificationInfo notificationInfo) {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {

            helper = new MimeMessageHelper(message, true);
            helper.setSubject(emailCustomerSubject);
            helper.setFrom(emailSender);
            helper.setTo("sbartolome@miu.edu");
            helper.setText(emailInfo, true);

            javaMailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sendEmailToOwner(String emailInfo, NotificationInfo notificationInfo) {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {

            helper = new MimeMessageHelper(message, true);
            helper.setSubject(emailOwnerSubject);
            helper.setFrom(emailSender);
            helper.setTo(emailOwner);
            helper.setText(emailInfo, true);

            javaMailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
