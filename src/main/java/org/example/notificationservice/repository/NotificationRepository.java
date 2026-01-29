package org.example.notificationservice.repository;

import org.example.notificationservice.model.Notification;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository {
    void save(Notification notification);

    List<Notification> findByUserId(String userId);

    List<Notification> findDueNotifications(LocalDateTime now);
}
