package com.miu.edu.cs590.project.notification.Controller;

import com.miu.edu.cs590.project.notification.dto.NotificationInfoDTO;
import com.miu.edu.cs590.project.notification.model.NotificationInfo;
import com.miu.edu.cs590.project.notification.service.NotificationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NotificationInfoController {


    @Autowired
    NotificationInfoService notificationInfoService;

    @GetMapping("/notifications")
    public List<NotificationInfoDTO> getNotifications() {
        return notificationInfoService.getAllNotifications();
    }

    @GetMapping("/notifications/{email}")
    public List<NotificationInfoDTO> getNotifications(@PathVariable String email) {
        return notificationInfoService.getByEmail(email);
    }

    // Testing Purposes Only
    @PostMapping("/notifications")
    public void addInformation(@Valid @RequestBody NotificationInfo notificationInfo) {
        notificationInfoService.saveNotificationInfo(notificationInfo);
    }
    @DeleteMapping ("/notifications/{email}")
    public void deleteNotification(@PathVariable String email) {
        notificationInfoService.deleteByEmail(email);
    }

}
