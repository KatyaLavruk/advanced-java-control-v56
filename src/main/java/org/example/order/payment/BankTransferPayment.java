package org.example.order.payment;

import org.example.order.domain.money;
import org.example.order.exception.PaymentException;

public class BankTransferPayment implements PaymentMetod {

    @Override
    public void pay(money amount) throws PaymentException {

        if (amount == null) {
            throw new PaymentException("Amount is null");
        }

        System.out.println("INFO: bank transfer success");
    }

    public money addCommission(money amount) {

        // комісія 1.5%
        return amount.multiply(1.015);

    }
}