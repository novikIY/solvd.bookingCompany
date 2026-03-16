package com.solvd.bookingcompany.service;

import com.solvd.bookingcompany.domain.Apartment;
import com.solvd.bookingcompany.domain.Booking;
import com.solvd.bookingcompany.domain.Customer;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class BookingCompany {

    private final List<Apartment> apartments = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();
    public static final org.apache.logging.log4j.Logger LOGGER =
            LogManager.getLogger(BookingCompany.class);

    public void addApartment(Apartment apartment) {
        apartments.add(apartment);
    }

    public void registerCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Apartment> searchAvailableApartments() {
        List<Apartment> available = new ArrayList<>();

        for (Apartment apartment : apartments) {
            if (apartment.isAvailable()) {
                available.add(apartment);
            }
        }

        return available;
    }

    public Booking createBooking(Long id, Apartment apartment, Customer customer,
                                 java.time.LocalDate checkIn,
                                 java.time.LocalDate checkOut,
                                 double price) throws Exception {

        Booking booking = new Booking(id, apartment, checkIn, checkOut, price);

        bookings.add(booking);

        customer.saveBooking(booking);

        return booking;
    }
}
