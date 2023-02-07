package com.miu.edu.cs590.project.notification.service;

import com.miu.edu.cs590.project.notification.model.NotificationInfo;

public interface EmailService {
    void sendEmailWithImage(String emailInfo, NotificationInfo notificationInfo);
    void sendEmailToOwner(String emailInfo, NotificationInfo notificationInfo);
}
