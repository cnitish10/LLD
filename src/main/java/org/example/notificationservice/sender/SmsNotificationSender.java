package org.example.notificationservice.sender;

import org.example.notificationservice.model.Notification;

public class SmsNotificationSender implements NotificationSender{
    @Override
    public void send(Notification notification) {
        System.out.println("📱 SMS sent: " + notification.getContent());
    }
}
