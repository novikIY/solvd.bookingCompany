package com.solvd.bookingcompany.exceptions;

import com.solvd.bookingcompany.domain.Apartment;

public class ApartmentNotAvailableException extends Exception {

    public ApartmentNotAvailableException(String message) {
        super(message);
    }

    public void bookApartment(Apartment apartment) throws ApartmentNotAvailableException {
        if (apartment.isAvailable()) {
            throw new ApartmentNotAvailableException("Apartment is not available for selected dates");
        }
    }
}