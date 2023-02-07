package com.miu.edu.cs590.project.producer.service;

import com.miu.edu.cs590.project.producer.common.NotificationInfo;

public interface KafkaSenderService {

    void receiveEvent(NotificationInfo notificationInfo);

}
