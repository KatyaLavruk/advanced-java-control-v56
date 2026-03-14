package org.example.order.payment;

import org.example.order.domain.money;
import org.example.order.exception.PaymentException;

public class CardPayment implements PaymentMetod {

    @Override
    public void pay(money amount) throws PaymentException {

        if (amount.getAmount() > 25000) {
            throw new PaymentException("Card payment limit is 25000");
        }

        System.out.println("INFO: card payment success");
    }
}