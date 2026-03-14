package org.example.order.notification;

import org.example.order.domain.order;
import org.example.order.exception.NotificationException;

public class ConsoleNotificationService implements NotificationService {

    @Override
    public void send(order order) throws NotificationException {

        try {
            System.out.println("INFO: notification sent to " + order.getCustomerEmail().getValue());
        } catch (Exception e) {
            throw new NotificationException("Notification failed", e);
        }

    }
}