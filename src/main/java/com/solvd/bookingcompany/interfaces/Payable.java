package com.solvd.bookingcompany.interfaces;

import com.solvd.bookingcompany.exceptions.PaymentFailedException;

public interface Payable {
    void pay() throws PaymentFailedException;
}
