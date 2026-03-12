package com.solvd.bookingcompany.enums;

public enum ApartmentType {
    STUDIO(1, "Studio apartment suitable for 1-2 guests"),
    ONE_BEDROOM(2, "One-bedroom apartment suitable for 2-3 guests"),
    TWO_BEDROOM(3, "Two-bedroom apartment suitable for 4-5 guests"),
    PENTHOUSE(4, "Luxury penthouse with 3+ bedrooms");

    private final int capacity;
    private final String description;

    ApartmentType(int capacity, String description) {
        this.capacity = capacity;
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }
}