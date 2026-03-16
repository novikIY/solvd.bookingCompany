package com.solvd.bookingcompany.payment;

import com.solvd.bookingcompany.domain.Booking;
import com.solvd.bookingcompany.domain.CreditCard;
import com.solvd.bookingcompany.enums.PaymentMethod;
import com.solvd.bookingcompany.enums.PaymentStatus;
import com.solvd.bookingcompany.exceptions.BookingNotFoundException;
import com.solvd.bookingcompany.exceptions.InvalidPaymentAmountException;
import com.solvd.bookingcompany.exceptions.PaymentFailedException;
import org.apache.logging.log4j.LogManager;

public class CreditCardPayment extends Payment {

    private CreditCard creditCard;
    public static final org.apache.logging.log4j.Logger LOGGER =
            LogManager.getLogger(CreditCardPayment.class);

    public CreditCardPayment(Booking booking, Double amount, CreditCard creditCard) {
        super(booking, amount);
        this.creditCard = creditCard;
    }

    public CreditCardPayment(Long id, Booking booking, Double amount,
                             CreditCard creditCard)
            throws BookingNotFoundException, InvalidPaymentAmountException {
        super(id, booking, amount, PaymentMethod.CREDIT_CARD);
        this.creditCard = creditCard;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public void pay() throws PaymentFailedException {
        LOGGER.info("Paying with Credit Card: {}", creditCard.getMaskedNumber());

        boolean success = true;

        if (!success) {
            throw new PaymentFailedException("Credit Card main.java.com.solvd.bookingcompany.payment failed");
        }

        setStatus(PaymentStatus.PAID);
    }

    @Override
    public String getEntityName() {
        return "main.java.com.solvd.bookingcompany.payment.CreditCardPayment";
    }
}