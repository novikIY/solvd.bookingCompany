package com.solvd.bookingcompany.payment;

import com.solvd.bookingcompany.enums.PaymentMethod;
import java.time.LocalDateTime;


public record TransactionRecord(String transactionId, LocalDateTime date,
                                double amount, PaymentMethod method) {
}
