package ru.ylab.in.models;

import ru.ylab.in.service.Bank;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Person {
    private Integer id;
    private String login;
    private String password;

    private double balance;

    public Person(String login, String password) {
        this.login = login;
        this.password = password;
        this.balance = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person(Integer id, String login, String password, double balance) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.balance = balance;
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
}
