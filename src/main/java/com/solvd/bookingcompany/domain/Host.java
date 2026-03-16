package com.solvd.bookingcompany.domain;

import org.apache.logging.log4j.LogManager;

public class Host extends User {

    private Double rating;
    public static final org.apache.logging.log4j.Logger LOGGER =
            LogManager.getLogger(Host.class);

    public Host() {
    }

    public Host(Long id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email);
    }

    public double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String getRole() {
        return "Host";
    }

    @Override
    public String getEntityName() {
        return "Host";
    }
}