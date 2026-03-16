package com.solvd.bookingcompany.domain;

import java.util.*;
import com.solvd.bookingcompany.collection.LinkedList;
import org.apache.logging.log4j.LogManager;

public class Customer extends User {

    private String phoneNumber;
    private Set<Booking> bookings = new HashSet<>();
    private Map<Long, Booking> bookingsById = new HashMap<>();
    private final LinkedList<Booking> bookingHistory = new LinkedList<>();
    public static final org.apache.logging.log4j.Logger LOGGER =
            LogManager.getLogger(Customer.class);

    public Customer() {
    }

    public Customer(Long id, String firstName, String lastName, String email,
                    String phoneNumber) {
        super(id, firstName, lastName, email);
        this.phoneNumber = phoneNumber;
    }

    public void saveBooking(Booking booking) {
        if (booking != null) {
            bookingHistory.save(booking);

            bookings.add(booking);
            bookingsById.put(booking.getId(), booking);
        }
    }

    public Booking getBookingFromHistory(int index) {
        return bookingHistory.get(index);
    }

    public boolean removeBookingFromHistory(int index) {
        Booking booking = bookingHistory.get(index);
        if (booking == null) return false;

        bookingHistory.remove(index);

        bookings.remove(booking);
        bookingsById.remove(booking.getId());

        return true;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Map<Long, Booking> getBookingsById() {
        return bookingsById;
    }

    public void setBookingsById(Map<Long, Booking> bookingsById) {
        this.bookingsById = bookingsById;
    }

    public LinkedList<Booking> getBookingHistory() {
        return bookingHistory;
    }

    @Override
    public String getRole() {
        return "Customer";
    }

    @Override
    public String getEntityName() {
        return "Customer";
    }
}