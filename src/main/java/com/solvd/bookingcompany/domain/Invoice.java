package com.solvd.bookingcompany.domain;

import com.solvd.bookingcompany.enums.InvoiceStatus;
import com.solvd.bookingcompany.payment.Payment;
import org.apache.logging.log4j.LogManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Invoice extends BaseEntity {
    private Booking booking;
    private BigDecimal totalAmount;
    private List<Payment> payments = new ArrayList<>();
    private InvoiceStatus status;
    public static final org.apache.logging.log4j.Logger LOGGER =
            LogManager.getLogger(Invoice.class);

    public Invoice(Long id, Booking booking, BigDecimal totalAmount) {
        super(id);
        this.booking = booking;
        this.totalAmount = totalAmount;
        this.status = InvoiceStatus.CREATED;
    }

    public Invoice(Long id, Booking booking, BigDecimal totalAmount,
                   List<Payment> payments, InvoiceStatus status) {
        super(id);
        this.booking = booking;
        this.totalAmount = totalAmount;
        this.payments = payments;
        this.status = status;
    }

    public InvoiceRecord toRecord() {
        return new InvoiceRecord(getId(), booking != null ? booking.getId() : null,
                totalAmount != null ? totalAmount.doubleValue() : 0.0, status);
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {

        String bookingId;

        if (booking != null) {
            bookingId = String.valueOf(booking.getId());
        } else {
            bookingId = "null";
        }

        return "Invoice{" + "id=" + getId() + ", bookingId=" + bookingId +
                ", totalAmount=" + totalAmount + ", status=" + status +
                ", createdAt=" + getCreatedAt() + ", updatedAt=" + getUpdatedAt() + '}';
    }

    @Override
    public String getEntityName() {
        return "Invoice";
    }
}
