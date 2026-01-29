package org.example.notificationservice.repository;

import org.example.notificationservice.model.Notification;
import org.example.notificationservice.model.NotificationStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryNotificationRepository implements NotificationRepository{
    private final List<Notification> store = new ArrayList<>();

    @Override
    public void save(Notification notification) {
        store.add(notification);
    }

    @Override
    public List<Notification> findByUserId(String userId) {
        return store.stream()
                .filter(n -> n.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Notification> findDueNotifications(LocalDateTime now) {
        return store.stream()
                .filter(n -> n.getStatus() == NotificationStatus.SCHEDULED)
                .filter(n -> !n.getScheduledAt().isAfter(now))
                .collect(Collectors.toList());
    }
}
