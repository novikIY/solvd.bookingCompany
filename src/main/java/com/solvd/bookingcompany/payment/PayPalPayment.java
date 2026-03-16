package com.solvd.bookingcompany.payment;

import com.solvd.bookingcompany.domain.Booking;
import com.solvd.bookingcompany.domain.PayPalAccount;
import com.solvd.bookingcompany.enums.PaymentMethod;
import com.solvd.bookingcompany.enums.PaymentStatus;
import com.solvd.bookingcompany.exceptions.BookingNotFoundException;
import com.solvd.bookingcompany.exceptions.InvalidPaymentAmountException;
import com.solvd.bookingcompany.exceptions.PaymentFailedException;
import org.apache.logging.log4j.LogManager;


public class PayPalPayment extends Payment {

    private PayPalAccount payPalAccount;
    public static final org.apache.logging.log4j.Logger LOGGER =
            LogManager.getLogger(PayPalPayment.class);

    public PayPalPayment(Long id, Booking booking, Double amount, PayPalAccount payPalAccount)
            throws BookingNotFoundException, InvalidPaymentAmountException {
        super(id, booking, amount, PaymentMethod.PAYPAL);
        this.payPalAccount = payPalAccount;
    }

    public PayPalAccount getPayPalAccount() {
        return payPalAccount;
    }

    public void setPayPalAccount(PayPalAccount payPalAccount) {
        this.payPalAccount = payPalAccount;
    }

    @Override
    public void pay() throws PaymentFailedException {
        LOGGER.info("Paying with PayPal: {}", payPalAccount.getEmail());

        boolean success = true;

        if (!success) {
            throw new PaymentFailedException("PayPal failed");
        }

        setStatus(PaymentStatus.PAID);
    }

    @Override
    public String getEntityName() {
        return "PayPalPayment";
    }
}