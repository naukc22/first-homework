package ru.ylab;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Person {

    private String login;
    private String password;

    private double balance;

    private Scanner sc = Bank.sc;

    private ArrayList<Transaction> transactions = new ArrayList<>(); // список всех транзакицй
    public Person(String login, String password) {
        this.login = login;
        this.password = password;
        this.balance = 0;
    }

    // Метод снятия
    public void debet() {

        System.out.println("Введите сумму снять");
        double amount = sc.nextDouble();

        if ( (balance - amount) >= 0) {
            UUID transactionID = UUID.randomUUID();

            balance = balance - amount;
            System.out.printf("Вы успешно сняли %s.",amount);
            System.out.println("");
            Transaction trans = new Transaction("Снятие", amount,transactionID);
            transactions.add(trans);
        } else {
            System.out.println("На вашем счете недостаточно средств");
        }

    }

    // Метод пополнения
    public void credit() {
        System.out.println("Введите сумму пополнения");
        double amount = sc.nextDouble();
        if( amount <= 0) {
            System.out.println("Введите число больше чем 0");
        } else {
            UUID transactionID = UUID.randomUUID();
            balance = balance + amount;
            Transaction trans = new Transaction("Пополнение", amount,transactionID);
            transactions.add(trans);
            System.out.printf("Вы успешно пополнили баланс на %s.",amount);
            System.out.println("");
        }
    }

    // Просмотр истории операций
    public void showTransactionStory() {
        for (Transaction trans :
                transactions) {
            System.out.println(trans);
        }
    }


    // Setter, getters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
