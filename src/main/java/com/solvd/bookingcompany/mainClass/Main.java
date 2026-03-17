package com.solvd.bookingcompany.mainClass;

import com.solvd.bookingcompany.collection.LinkedList;
import com.solvd.bookingcompany.database.ConnectionPool;
import com.solvd.bookingcompany.database.MyConnection;
import com.solvd.bookingcompany.domain.*;
import com.solvd.bookingcompany.exceptions.*;
import com.solvd.bookingcompany.payment.CreditCardPayment;
import com.solvd.bookingcompany.payment.Payment;
import com.solvd.bookingcompany.service.BookingCompany;

import com.solvd.bookingcompany.textparser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        ConnectionPool pool = ConnectionPool.getInstance();

        MyConnection connection = pool.getConnection();
        LOGGER.info("Available connections: {}", pool.getAvailableConnections());

        if (connection != null) {
            LOGGER.info("Connection taken from pool");
            pool.releaseConnection(connection);
        }

        BookingCompany company = new BookingCompany();

        Apartment apartment = new Apartment();
        apartment.setTitle("Nice apartment");

        Availability availability = new Availability();
        availability.setFrom(LocalDate.of(2026, 3, 1));
        availability.setTo(LocalDate.of(2026, 3, 10));
        availability.setAvailable(true);

        List<Availability> availabilities = new ArrayList<>();
        availabilities.add(availability);
        apartment.setAvailabilities(availabilities);

        company.addApartment(apartment);

        Customer customer = new Customer();
        customer.setFirstName("Jack");
        customer.setLastName("Smith");

        company.addCustomer(customer);

        LinkedList<Booking> bookingHistory = customer.getBookingHistory();

        LocalDate checkIn = LocalDate.of(2026, 3, 2);
        LocalDate checkOut = LocalDate.of(2026, 3, 3);
        double totalPrice = 300.0;

        List<Apartment> availableApartments =
                company.findAvailableApartments(checkIn, checkOut);

        LOGGER.info("Found {} available apartments", availableApartments.size());

        availableApartments.forEach(a ->
                LOGGER.info("Available apartment: {}", a.getTitle()));

        Booking booking = null;

        try {
            booking = company.createBooking(
                    1L,
                    apartment,
                    customer,
                    checkIn,
                    checkOut,
                    totalPrice
            );

            LOGGER.info("Booking created: {}", booking);

        } catch (InvalidBookingDatesException | ApartmentNotAvailableException e) {
            LOGGER.error("Invalid booking dates", e);
        }

        CreditCard creditCard = new CreditCard(
                "1234567812345678",
                "JACK SMITH",
                "12/27",
                "123"
        );

        if (booking != null) {

            try {

                Payment payment = new CreditCardPayment(
                        1L,
                        booking,
                        totalPrice,
                        creditCard
                );

                LOGGER.info("Payment created: {}", payment);

                payment.pay();

                LOGGER.info("Payment successful");


                Invoice invoice = new Invoice(1L, booking, BigDecimal.valueOf(300.0));
                InvoiceRecord record = invoice.toRecord();

                LOGGER.info("Invoice record created: {}", record);

            } catch (BookingNotFoundException
                     | InvalidPaymentAmountException
                     | PaymentFailedException e) {

                LOGGER.error("Payment error", e);
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


        TextParser parser = new TextParser();

        Map<String, Integer> result = parser.parseText("input.txt");

        result.forEach((word, count) ->
                LOGGER.info("{} -> {}", word, count));

        parser.saveResult(result, "output.txt");
    }
}