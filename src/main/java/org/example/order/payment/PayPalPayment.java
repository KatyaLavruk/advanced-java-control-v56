package org.example.order.payment;

import org.example.order.domain.money;
import org.example.order.exception.PaymentException;

public class PayPalPayment implements PaymentMetod {

    @Override
    public void pay(money amount) throws PaymentException {

        if (amount.getAmount() < 200) {
            throw new PaymentException("PayPal payment must be at least 200");
        }

        System.out.println("INFO: paypal payment success");
    }
}