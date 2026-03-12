package com.solvd.bookingcompany.domain;

public class Host extends User {

    private Double rating;

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