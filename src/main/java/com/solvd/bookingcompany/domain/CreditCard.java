package com.solvd.bookingcompany.domain;

import com.solvd.bookingcompany.payment.TransactionRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class CreditCard {

    private final String cardNumber;
    private final String cardHolder;
    private final String expiryDate;
    private final String cvv;
    private final List<TransactionRecord> transactions = new ArrayList<>();

    public CreditCard(String cardNumber, String cardHolder, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public void addTransaction(TransactionRecord tr) {
        transactions.add(tr);
    }

    public List<TransactionRecord> getTransactions() {
        return transactions;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public final String getMaskedNumber() {
        return "**** **** **** " +
                cardNumber.substring(cardNumber.length() - 4);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        CreditCard cardObject = (CreditCard) o;

        if (cardObject.hashCode() != this.hashCode()) return false;

        if (this.cardNumber != cardObject.cardNumber) return false;
        if (this.cardHolder != cardObject.cardHolder) return false;
        if (this.expiryDate != cardObject.expiryDate) return false;
        if (this.cvv != cardObject.cvv) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardHolder, expiryDate, cvv);
    }
}
