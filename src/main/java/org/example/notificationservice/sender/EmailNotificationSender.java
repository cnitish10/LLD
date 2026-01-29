package org.example.notificationservice.sender;

import org.example.notificationservice.model.Notification;

public class EmailNotificationSender implements NotificationSender{
    @Override
    public void send(Notification notification) {
        System.out.println("📧 Email sent: " + notification.getContent());
    }
}
