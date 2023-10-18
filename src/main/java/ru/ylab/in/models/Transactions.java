package ru.ylab.in.models;

import java.util.UUID;

// Транзакция
public class Transactions {
    UUID ID;
    Integer personID;
    String operationName;
    double amount;

    public Transactions(UUID ID, Integer personID, String operationName, double amount) {
        this.ID = ID;
        this.personID = personID;
        this.operationName = operationName;
        this.amount = amount;
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
        return "Transaction{" +
                "ID=" + ID +
                ", personID=" + personID +
                ", operationName='" + operationName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
