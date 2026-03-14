package org.example.order.processor;

import org.example.order.domain.category;
import org.example.order.domain.money;
import org.example.order.domain.order;
import org.example.order.domain.orderItem;
import org.example.order.domain.orderStatus;
import org.example.order.exception.AppException;
import org.example.order.exception.CategoryMixException;
import org.example.order.exception.InvalidStateException;
import org.example.order.exception.ValidationException;
import org.example.order.payment.BankTransferPayment;
import org.example.order.payment.PaymentMetod;

import java.util.HashSet;
import java.util.Set;

public class StandardOrderProcessor extends OrderProcessorTemplate {

    @Override
    protected void validate(order order) throws AppException {
        if (order == null) {
            System.out.println("WARN: order is null");
            throw new ValidationException("Order is null");
        }

        if (order.getItems().length < 2) {
            System.out.println("WARN: less than 2 items");
            throw new ValidationException("Order must contain at least 2 items");
        }

        if (order.getStatus() != orderStatus.NEW) {
            System.out.println("WARN: wrong order state");
            throw new InvalidStateException("Only NEW order can be paid");
        }
    }

    @Override
    protected void validateCategoryMix(order order) throws AppException {
        Set<category> categories = new HashSet<>();

        for (orderItem item : order.getItems()) {
            categories.add(item.getCategory());
        }

        if (categories.size() < 2) {
            System.out.println("WARN: category mix error");
            throw new CategoryMixException("Order must contain at least 2 different categories");
        }
    }

    @Override
    protected void calculate(order order) {
        money total = new money(0);
        Set<category> categories = new HashSet<>();

        for (orderItem item : order.getItems()) {
            total = total.add(item.getTotal());
            categories.add(item.getCategory());
        }

        if (categories.size() >= 3) {
            total = total.multiply(0.95);
        }

        order.setTotal(total);
        System.out.println("INFO: total calculated = " + total);
    }

    @Override
    protected void pay(order order, PaymentMetod paymentMethod) throws AppException {
        if (order.getStatus() == orderStatus.PAID
                || order.getStatus() == orderStatus.SHIPPED
                || order.getStatus() == orderStatus.DELIVERED) {
            throw new InvalidStateException("Repeated payment is forbidden");
        }

        money amount = order.getTotal();

        if (paymentMethod instanceof BankTransferPayment bankTransferPayment) {
            amount = bankTransferPayment.addCommission(amount);
        }

        try {
            paymentMethod.pay(amount);
            order.setStatus(orderStatus.PAID);
            System.out.println("INFO: order paid");
        } catch (AppException e) {
            System.out.println("WARN: payment failed");
            throw e;
        } catch (Exception e) {
            System.out.println("ERROR: unexpected payment error");
            throw new AppException("Unexpected payment error", e);
        }
    }
}