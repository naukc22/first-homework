package ru.ylab;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner sc =  new Scanner(System.in);
    private static Bank bank  = new Bank();
    private static boolean exitRequest = false;

    public static void main(String[] args) {
        MenuService menuService = new MenuService();

        while (!exitRequest) {
            menuService.showNotAuthMenu();
            String choice = sc.nextLine();
            switch (choice) {
                case "1" : bank.registrationPerson();
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