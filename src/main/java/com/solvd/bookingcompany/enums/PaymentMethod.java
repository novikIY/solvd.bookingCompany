package com.solvd.bookingcompany.enums;

public enum PaymentMethod {
    CREDIT_CARD("Credit Card", true),
    PAYPAL("PayPal", true),
    CASH("Cash", false);

    private final String displayName;
    private final boolean online;

    PaymentMethod(String displayName, boolean online) {
        this.displayName = displayName;
        this.online = online;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isOnline() {
        return online;
    }
}
