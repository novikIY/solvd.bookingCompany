package com.solvd.bookingcompany.search;

import java.time.LocalDate;

public class SearchCriteria {

    private LocalDate checkIn;
    private LocalDate checkOut;

    public static final int MIN_STAY_DAYS;
    public static final int MAX_STAY_DAYS;

    static {
        MIN_STAY_DAYS = 1;
        MAX_STAY_DAYS = 30;
    }

    public SearchCriteria(LocalDate checkIn, LocalDate checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
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
}
