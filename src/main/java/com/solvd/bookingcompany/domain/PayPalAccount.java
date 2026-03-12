package com.solvd.bookingcompany.domain;

import com.solvd.bookingcompany.payment.TransactionRecord;

import java.util.ArrayList;
import java.util.List;

public class PayPalAccount {

    private String email;
    private final List<TransactionRecord> transactions = new ArrayList<>();

    public PayPalAccount(String email) {
        this.email = email;
    }

    public void addTransaction(TransactionRecord tr) {
        transactions.add(tr);
    }

    public List<TransactionRecord> getTransactions() {
        return transactions;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PayPalAccount{" + "email='" + email + '\'' + ", transactions="
                + transactions + '}';
    }
}
