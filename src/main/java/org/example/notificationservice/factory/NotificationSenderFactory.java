package org.example.notificationservice.factory;

import org.example.notificationservice.model.NotificationType;
import org.example.notificationservice.sender.EmailNotificationSender;
import org.example.notificationservice.sender.InAppNotificationSender;
import org.example.notificationservice.sender.NotificationSender;
import org.example.notificationservice.sender.SmsNotificationSender;

public class NotificationSenderFactory {
    public static NotificationSender getSender(NotificationType type) {
        return switch (type) {
            case EMAIL -> new EmailNotificationSender();
            case SMS -> new SmsNotificationSender();
            case IN_APP -> new InAppNotificationSender();
            default -> throw new IllegalArgumentException("Unsupported notification type");
        };
    }
}
