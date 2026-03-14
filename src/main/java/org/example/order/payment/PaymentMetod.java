package org.example.order.payment;

import org.example.order.domain.money;
import org.example.order.exception.PaymentException;

public interface PaymentMetod {

    void pay(money amount) throws PaymentException;

}