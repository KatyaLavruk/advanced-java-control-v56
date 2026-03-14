package org.example.order.domain;

import java.util.Arrays;
import java.util.UUID;

public class order {
    private final String id;
    private final email customerEmail;
    private final orderItem[] items;
    private orderStatus status;
    private money total;

    public order(email customerEmail, orderItem[] items) {
        this(UUID.randomUUID().toString(), customerEmail, items);
    }

    public order(String id, email customerEmail, orderItem[] items) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id is empty");
        }
        if (customerEmail == null) {
            throw new IllegalArgumentException("Email is null");
        }
        if (items == null) {
            throw new IllegalArgumentException("Items are null");
        }

        this.id = id;
        this.customerEmail = customerEmail;
        this.items = Arrays.copyOf(items, items.length);
        this.status = orderStatus.NEW;
        this.total = new money(0);
    }

    public String getId() {
        return id;
    }

    public email getCustomerEmail() {
        return customerEmail;
    }

    public orderItem[] getItems() {
        return Arrays.copyOf(items, items.length);
    }

    public orderStatus getStatus() {
        return status;
    }

    public money getTotal() {
        return total;
    }

    public void setTotal(money total) {
        this.total = total;
    }

    public void setStatus(orderStatus status) {
        this.status = status;
    }
}