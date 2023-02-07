package com.miu.edu.cs590.project.notification.service;

import com.miu.edu.cs590.project.notification.dto.NotificationInfoDTO;
import com.miu.edu.cs590.project.notification.model.NotificationAdapter;
import com.miu.edu.cs590.project.notification.model.NotificationInfo;
import com.miu.edu.cs590.project.notification.repository.NotificationInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationInfoServiceImpl implements NotificationInfoService {

    @Autowired
    NotificationInfoRepository notificationInfoRepository;

    @Override
    public List<NotificationInfoDTO> getAllNotifications() {
        return NotificationAdapter.convertToNotificationInfoDTOListFromNotificationInfoList(notificationInfoRepository.findAll());
    }

    @Override
    public List<NotificationInfoDTO> getByEmail(String email) {
        return NotificationAdapter.convertToNotificationInfoDTOListFromNotificationInfoList(notificationInfoRepository.findByEmail(email));
    }

    @Override
    public NotificationInfoDTO getByFullName(String userName) {
        return NotificationAdapter.convertToNotificationInfoDTOFromNotificationInfo(notificationInfoRepository.findByFullName(userName));
    }

    @Override
    public void deleteByEmail(String email) {
        notificationInfoRepository.deleteByEmail(email);
    }

    @Override
    public void saveNotificationInfo(NotificationInfo notificationInfo) {
        notificationInfoRepository.save(notificationInfo);
    }
}
