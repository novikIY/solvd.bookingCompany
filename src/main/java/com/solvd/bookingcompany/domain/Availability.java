package com.solvd.bookingcompany.domain;

import com.solvd.bookingcompany.interfaces.Searchable;
import com.solvd.bookingcompany.search.SearchCriteria;
import org.apache.logging.log4j.LogManager;

import java.time.LocalDate;

public final class Availability extends BaseEntity implements Searchable {

    private LocalDate from;
    private LocalDate to;
    private Boolean available;
    public static final org.apache.logging.log4j.Logger LOGGER =
            LogManager.getLogger(Availability.class);

    public Availability() {
    }

    public Availability(Long id, LocalDate from, LocalDate to,
                        boolean available) {
        super(id);
        this.from = from;
        this.to = to;
        this.available = available;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public boolean coversPeriod(LocalDate checkIn, LocalDate checkOut) {
        return available && !checkIn.isBefore(from) && !checkOut.isAfter(to);
    }

    @Override
    public String getEntityName() {
        return "Availability";
    }

    @Override
    public String toString() {
        return "Availability{" + "id=" + getId() + ", from=" + from +
                ", to=" + to + ", available=" + available + '}';
    }

    @Override
    public boolean matches(SearchCriteria criteria) {
        if (!available) {
            return false;
        }

        if (criteria.getCheckIn().isBefore(from)) {
            return false;
        }

        if (criteria.getCheckOut().isAfter(to)) {
            return false;
        }
        return true;
    }
}
