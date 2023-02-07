package com.miu.edu.cs590.project.notification.repository;

import com.miu.edu.cs590.project.notification.model.NotificationInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationInfoRepository extends MongoRepository<NotificationInfo, String> {

    List<NotificationInfo> findByEmail(String email);
    NotificationInfo findByFullName(String userName);
    void deleteByEmail(String email);
}
