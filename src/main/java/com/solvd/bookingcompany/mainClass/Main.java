package com.solvd.bookingcompany.mainClass;

import com.solvd.bookingcompany.collection.LinkedList;
import com.solvd.bookingcompany.database.ConnectionPool;
import com.solvd.bookingcompany.domain.*;
import com.solvd.bookingcompany.exceptions.*;
import com.solvd.bookingcompany.payment.CreditCardPayment;
import com.solvd.bookingcompany.payment.Payment;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.solvd.bookingcompany.service.BookingCompany;
import org.apache.logging.log4j.LogManager;

public class Main {
    public static final org.apache.logging.log4j.Logger LOGGER =
            LogManager.getLogger(BookingCompany.class);

    public static void main(String[] args) {

        ConnectionPool pool = new ConnectionPool(
                "jdbc:mysql://localhost:1234/bookingdb",
                "root",
                "password",
                3
        );

        LOGGER.info("Connection pool loaded.");
        LOGGER.info("Available connections: {}", pool.getAvailableConnections());

        Connection connection = pool.getConnection();

        if (connection != null) {
            LOGGER.info("Using connection from pool...");
            pool.releaseConnection(connection);
        }

        LOGGER.info("Connections after release: {}", pool.getAvailableConnections());

        pool.closeAllConnections();
        LOGGER.info("All connections closed.");

        Apartment apartment = new Apartment();
        apartment.setTitle("Nice apartment");

        Availability availability = new Availability();
        availability.setFrom(LocalDate.of(2026, 3, 1));
        availability.setTo(LocalDate.of(2026, 3, 10));
        availability.setAvailable(true);

        List<Availability> availabilities = new ArrayList<>();
        availabilities.add(availability);
        apartment.setAvailabilities(availabilities);

        Customer customer = new Customer();
        customer.setFirstName("Jack");
        customer.setLastName("Smith");

        LinkedList<Booking> bookingHistory = customer.getBookingHistory();

        LocalDate checkIn = LocalDate.of(2026, 3, 2);
        LocalDate checkOut = LocalDate.of(2026, 3, 3);
        double totalPrice = 300.0;

        Booking booking = null;
        Long bookingId = 1L;

        try {
            booking = new Booking(bookingId, apartment, checkIn, checkOut, totalPrice);
            LOGGER.info("Booking created: {}", booking);

            bookingHistory.save(booking);
            LOGGER.info("Booking added to customer's history. Size: {}", bookingHistory.size());

        } catch (InvalidBookingDatesException e) {
            e.printStackTrace();
        }

        try {
            if (booking != null && !apartment.isAvailable()) {
                throw new ApartmentNotAvailableException("Apartment is not available");
            }
            LOGGER.info("Apartment is available for booking.");
        } catch (ApartmentNotAvailableException e) {
            e.printStackTrace();
        }

        CreditCard creditCard = new CreditCard("1234567812345678",
                "JACK SMITH", "12/27", "123");

        if (booking != null) {
            Long paymentId = 1L;
            try {
                Payment payment = new CreditCardPayment(paymentId, booking, totalPrice, creditCard);
                LOGGER.info("Payment created: {}", payment);

                payment.pay();
                LOGGER.info("Payment successful");

                Invoice invoice = new Invoice(1L, booking, totalPrice);
                InvoiceRecord record = invoice.toRecord();

                LOGGER.info("Invoice record created: {}", record);

            } catch (BookingNotFoundException e) {
                e.printStackTrace();
            } catch (InvalidPaymentAmountException e) {
                e.printStackTrace();
            } catch (PaymentFailedException e) {
                e.printStackTrace();
            }
        }

        LOGGER.info("First booking from history: {}", bookingHistory.get(0));

        bookingHistory.remove(0);
        LOGGER.info("Booking removed. Size now: {}", bookingHistory.size());

        LOGGER.info("Available dates:");

        availabilities.stream()
                .filter(a -> a.isAvailable())
                .forEach(a ->
                        LOGGER.info("From: {} To: {}", a.getFrom(), a.getTo()));

        LOGGER.info("Availabilities sorted by start date:");

        availabilities.stream()
                .sorted((a1, a2) -> a1.getFrom().compareTo(a2.getFrom()))
                .forEach(a ->
                        LOGGER.info("From: {} To: {}", a.getFrom(), a.getTo()));
    }
}