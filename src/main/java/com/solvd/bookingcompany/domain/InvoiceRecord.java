package com.solvd.bookingcompany.domain;

import com.solvd.bookingcompany.enums.InvoiceStatus;

public record InvoiceRecord(Long invoiceId, Long bookingId,
                            double amount, InvoiceStatus status) {
}
