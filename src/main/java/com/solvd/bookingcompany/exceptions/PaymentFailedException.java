package com.solvd.bookingcompany.exceptions;

public class PaymentFailedException extends Exception {

    public PaymentFailedException(String message) {
        super(message);
    }

    public PaymentFailedException() {
        super("Payment has failed");
    }
}
