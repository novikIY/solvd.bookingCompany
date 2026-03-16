package com.solvd.bookingcompany.domain;

import org.apache.logging.log4j.LogManager;

public class Review extends BaseEntity {

    private Integer rating;
    private String comment;
    private Booking booking;
    public static final org.apache.logging.log4j.Logger LOGGER =
            LogManager.getLogger(Review.class);

    public Review() {
    }

    public Review(Long id, Booking booking, Integer rating, String comment){
        super(id);
        this.booking = booking;
        this.rating = rating;
        this.comment = comment;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String getEntityName() {
        return "Review";
    }
}
