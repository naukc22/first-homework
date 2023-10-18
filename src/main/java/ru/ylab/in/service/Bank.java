package ru.ylab.in.service;

import ru.ylab.in.controller.Main;
import ru.ylab.in.models.Person;
import ru.ylab.in.repository.Statements;
import ru.ylab.in.utils.MenuService;
import ru.ylab.in.utils.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Bank {
    public static List<Person> people = new ArrayList<>();
    public static List<String> logins = new ArrayList<>();
    public static Scanner sc = Main.sc;
    public static MenuService menu = new MenuService();
    public static ArrayList<UUID> ids = new ArrayList<>();

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(
                    Properties.getPath(),
                    Properties.getLogin(),
                    Properties.getPassword());
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных");
        }
    }

    //Регистрация
    public static void registrationPerson() throws SQLException {
        System.out.println("Введите логин");
        String login = sc.nextLine();
        System.out.println("Введите пароль");
        String pass = sc.nextLine();
        if (!Statements.isExist(connection, login)) {
            try {
                Statements.insertPerson(connection, new Person(login, pass));
            } catch (SQLException e) {
                System.err.println("Ошибка базы данных при регистрации");;
            }
            System.out.println("Вы успешно зарегистрировались");
        }
    }

    // Проверка на уникальность логина

    // Логин
    public static void loginPerson() {
        Scanner sc =  new Scanner(System.in);

        System.out.println("Введите логин");
        String login = sc.nextLine();
        System.out.println("Введите пароль");
        String pass = sc.nextLine();
        Person pers = null;
        try {

                while (true) {
                    pers = Statements.personCheck(connection, login, pass);
                    boolean exitRequest = false;
                    if (pers != null) {
                        System.out.println("Авторизация прошла успешно");
                        while (!exitRequest) {
                            menu.showAuthMenu();
                            String answer = sc.nextLine();
                            switch (answer) {

                                case "1" : Statements.debit(connection, pers);
                                break;
                                case "2": Statements.credit(connection, pers);
                                break;
                                case "3": {
                                    System.out.printf("Ваш баланс : %s", pers.getBalance());
                                    System.out.println("");
                                    break;
                                }
                                case "4" : Statements.showTrans(connection, pers);
                                break;
                                case "5": exitRequest = true;
                                break;
                                default: System.out.println("Такой операции не существует");
                            }
                        }
                    }
                    break;
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
