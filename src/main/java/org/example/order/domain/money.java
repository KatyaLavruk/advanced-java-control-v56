package org.example.order.domain;

import java.util.Objects;

public class money {
    private final double amount;

    public money(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = Math.round(amount * 100.0) / 100.0;
    }

    public double getAmount() {
        return amount;
    }

    public money add(money other) {
        return new money(this.amount + other.amount);
    }

    public money multiply(double value) {
        return new money(this.amount * value);
    }

    @Override
    public String toString() {
        return "Money{amount=" + amount + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof money money)) return false;
        return Double.compare(money.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}