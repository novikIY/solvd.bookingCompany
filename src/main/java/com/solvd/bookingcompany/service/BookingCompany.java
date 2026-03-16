package com.solvd.bookingcompany.service;

import com.solvd.bookingcompany.domain.Apartment;
import com.solvd.bookingcompany.domain.Availability;
import com.solvd.bookingcompany.domain.Booking;
import com.solvd.bookingcompany.domain.Customer;
import com.solvd.bookingcompany.exceptions.InvalidBookingDatesException;
import org.apache.logging.log4j.LogManager;

import java.time.LocalDate;
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

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Apartment> findAvailableApartments(LocalDate from, LocalDate to) {

        List<Apartment> result = new ArrayList<>();

        for (Apartment apartment : apartments) {

            for (Availability availability : apartment.getAvailabilities()) {

                if (availability.isAvailable()
                        && !availability.getFrom().isAfter(from)
                        && !availability.getTo().isBefore(to)) {

                    result.add(apartment);
                    break;
                }
            }
        }
        return result;
    }

    public Booking createBooking(Long id, Apartment apartment, Customer customer,
                                 LocalDate checkIn, LocalDate checkOut,
                                 double price) throws InvalidBookingDatesException {

        Booking booking = new Booking(id, apartment, checkIn, checkOut, price);

        bookings.add(booking);

        customer.getBookingHistory().save(booking);

        return booking;
    }
}
