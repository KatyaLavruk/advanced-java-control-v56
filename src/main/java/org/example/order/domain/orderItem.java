package org.example.order.domain;

public class orderItem {
    private final String name;
    private final category category;
    private final int quantity;
    private final money price;

    public orderItem(String name, category category, int quantity, money price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is empty");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category is null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be > 0");
        }
        if (price == null) {
            throw new IllegalArgumentException("Price is null");
        }

        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public category getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public money getPrice() {
        return price;
    }

    public money getTotal() {
        return price.multiply(quantity);
    }
}