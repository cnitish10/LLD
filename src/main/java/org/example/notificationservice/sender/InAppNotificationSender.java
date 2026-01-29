package org.example.notificationservice.sender;

import org.example.notificationservice.model.Notification;

public class InAppNotificationSender implements NotificationSender{
    @Override
    public void send(Notification notification) {
        System.out.println("🔔 In-App notification stored: " + notification.getContent());
    }
}
