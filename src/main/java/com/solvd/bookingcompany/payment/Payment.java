package com.solvd.bookingcompany.payment;

import com.solvd.bookingcompany.domain.BaseEntity;
import com.solvd.bookingcompany.domain.Booking;
import com.solvd.bookingcompany.enums.PaymentMethod;
import com.solvd.bookingcompany.enums.PaymentStatus;
import com.solvd.bookingcompany.exceptions.BookingNotFoundException;
import com.solvd.bookingcompany.exceptions.InvalidPaymentAmountException;
import com.solvd.bookingcompany.exceptions.PaymentFailedException;
import com.solvd.bookingcompany.interfaces.Payable;

public abstract class Payment extends BaseEntity implements Payable {

    private Booking booking;
    private Double amount;
    private PaymentStatus status;
    private PaymentMethod method;

    public Payment(Booking booking, Double amount) {
    }

    public Payment(Long id, Booking booking, Double amount, PaymentMethod method)
            throws BookingNotFoundException, InvalidPaymentAmountException {
        super(id);

        if (booking == null) {
            throw new BookingNotFoundException("main.java.com.solvd.bookingcompany.domain.Booking not found for main.java.com.solvd.bookingcompany.payment");
        }

        if (amount == null || amount <= 0) {
            throw new InvalidPaymentAmountException("main.java.com.solvd.bookingcompany.payment.Payment amount must be greater than zero");
        }

        this.booking = booking;
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
        this.method = method;
    }

    public abstract void pay() throws PaymentFailedException;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    @Override
    public String toString() {

        String bookingId;

        if (booking != null) {
            bookingId = String.valueOf(booking.getId());
        } else {
            bookingId = "null";
        }

        return "Payment{" + "id=" + getId() + ", bookingId=" + bookingId +
                ", amount=" + amount + ", status=" + status + '}';
    }
}
