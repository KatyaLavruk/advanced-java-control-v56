package org.example.order.notification;

import org.example.order.domain.order;
import org.example.order.exception.NotificationException;

public interface NotificationService {

    void send(order order) throws NotificationException;

}