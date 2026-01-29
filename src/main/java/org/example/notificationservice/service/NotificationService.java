package org.example.notificationservice.service;

import org.example.notificationservice.factory.NotificationSenderFactory;
import org.example.notificationservice.model.Notification;
import org.example.notificationservice.model.NotificationStatus;
import org.example.notificationservice.repository.NotificationRepository;
import org.example.notificationservice.sender.NotificationSender;

import java.util.List;

public class NotificationService {
    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public void send(Notification notification) {
        try {
            NotificationSender sender =
                    NotificationSenderFactory.getSender(notification.getType());

            sender.send(notification);
            notification.setStatus(NotificationStatus.SENT);
        } catch (Exception e) {
            notification.setStatus(NotificationStatus.FAILED);
        }
    }

    public void create(Notification notification) {
        repository.save(notification);

        if (notification.getStatus() == NotificationStatus.PENDING) {
            send(notification);
        }
    }

    public List<Notification> getHistory(String userId) {
        return repository.findByUserId(userId);
    }
}
