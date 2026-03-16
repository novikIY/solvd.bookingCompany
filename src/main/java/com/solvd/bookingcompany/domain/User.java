package com.solvd.bookingcompany.domain;

import org.apache.logging.log4j.LogManager;

public abstract class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    public static final org.apache.logging.log4j.Logger LOGGER =
            LogManager.getLogger(User.class);

    public User() {
    }

    public User(Long id, String firstName, String lastName, String email) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFullName(boolean withEmail) {
        if (withEmail) {
            return firstName + " " + lastName + " " + email;
        }
        return getFullName();
    }

    public String getRole() {
        return "User";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}