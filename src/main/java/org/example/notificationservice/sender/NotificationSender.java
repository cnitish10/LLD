package org.example.notificationservice.sender;

import org.example.notificationservice.model.Notification;

public interface NotificationSender {
    void send(Notification notification);
}
