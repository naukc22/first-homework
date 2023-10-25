package ru.ylab.in.controller;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import ru.ylab.in.repository.LiquibaseStart;
import ru.ylab.in.service.Bank;
import ru.ylab.in.utils.MenuService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static Scanner sc =  new Scanner(System.in);
    private static Bank bank  = new Bank();
    private static boolean exitRequest = false;

    public static void main(String[] args) {

        LiquibaseStart.startConnection();

        while (!exitRequest) {
            MenuService.showNotAuthMenu();
            String choice = sc.nextLine();
            switch (choice) {
                case "1" :
                    try {
                        bank.registrationPerson();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "2" : bank.loginPerson();
                    break;
                case "3" : exitRequest = true;
                    break;
                default:
                    System.out.println("Такой операции не сущестувет");
            }

        }
    }
}