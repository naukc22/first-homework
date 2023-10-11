package ru.ylab;

import java.util.UUID;

// Транзакция
public class Transaction {
    String operationName;
    double amount;
    UUID ID;

    public Transaction(String operationName, double amount, UUID ID) {
        this.operationName = operationName;
        this.amount = amount;
        this.ID = ID;
    }


    // Getters and Setters
    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "{" + operationName + ". Cумма = " + amount +
                '}';
    }
}
