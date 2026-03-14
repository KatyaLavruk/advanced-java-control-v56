package org.example.order.processor;

import org.example.order.domain.order;
import org.example.order.domain.orderStatus;
import org.example.order.exception.AppException;
import org.example.order.payment.PaymentMetod;

public abstract class OrderProcessorTemplate {

    public final void process(order order, PaymentMetod paymentMetod) throws AppException {
        System.out.println("INFO: start processing");
        validate(order);
        validateCategoryMix(order);
        calculate(order);
        pay(order, paymentMetod);
        finish(order);
        System.out.println("INFO: processing finished");
    }

    protected abstract void validate(order order) throws AppException;

    protected abstract void validateCategoryMix(order order) throws AppException;

    protected abstract void calculate(order order) throws AppException;

    protected abstract void pay(order order, PaymentMetod paymentMethod) throws AppException;

    protected void finish(order order) {
        order.setStatus(orderStatus.SHIPPED);
        System.out.println("INFO: order shipped");
        order.setStatus(orderStatus.DELIVERED);
        System.out.println("INFO: order delivered");
    }
}