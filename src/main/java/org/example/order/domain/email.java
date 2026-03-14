package org.example.order.domain;

public class email {
    private final String value;

    public email(String value) {
        if (value == null || value.isBlank() || !value.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}