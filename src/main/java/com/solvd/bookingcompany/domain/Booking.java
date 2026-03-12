package com.solvd.bookingcompany.domain;

import com.solvd.bookingcompany.enums.BookingStatus;
import com.solvd.bookingcompany.exceptions.InvalidBookingDatesException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Booking extends BaseEntity {

    private Apartment apartment;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BookingStatus status;
    private Double totalPrice;

    public Booking() {
    }

    public Booking(Long id, Apartment apartment, LocalDate checkIn,
                   LocalDate checkOut) {
        super(id);
        this.apartment = apartment;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = BookingStatus.CREATED;
    }

    public Booking(Long id, Apartment apartment, LocalDate checkIn,
                   LocalDate checkOut, Double totalPrice) throws InvalidBookingDatesException {

        if (checkIn == null || checkOut == null) {
            throw new InvalidBookingDatesException("Check-in and check-out dates must not be null");
        }

        if (!checkIn.isBefore(checkOut)) {
            throw new InvalidBookingDatesException("Check-in date must be before check-out date");
        }
        super(id);
        this.apartment = apartment;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = BookingStatus.CREATED;
        this.totalPrice = totalPrice;
    }

    public int calculateNights() {
        return (int) ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public static int calculateNights(LocalDate checkIn, LocalDate checkOut) {
        return (int) ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Booking{" + "id=" + getId() + ", apartment=" + apartment.getTitle() +
                ", checkIn=" + checkIn + ", checkOut=" + checkOut + ", status=" + status + "," +
                " totalPrice=" + totalPrice + '}';
    }

    @Override
    public String getEntityName() {
        return "Booking";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        Booking bookingObject = (Booking) o;

        if (bookingObject.hashCode() != this.hashCode()) return false;

        if (this.getApartment() != bookingObject.getApartment()) return false;
        if (this.getCheckIn() != bookingObject.getCheckIn()) return false;
        if (this.getCheckOut() != bookingObject.getCheckOut()) return false;
        if (this.getStatus() != bookingObject.getStatus()) return false;
        if (this.getTotalPrice() != bookingObject.getTotalPrice()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getApartment(), getCheckIn(), getCheckOut(),
                getStatus(), getTotalPrice());
    }
}
