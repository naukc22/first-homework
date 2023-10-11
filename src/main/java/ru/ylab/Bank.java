package ru.ylab;

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

    //Регистрация
    public static void registrationPerson(){
        System.out.println("Введите логин");
        String login = sc.nextLine();
        System.out.println("Введите пароль");
        String pass = sc.nextLine();
        if (isLoginCorrect(login)) {
            people.add(new Person(login,pass));
            logins.add(login);
            System.out.println("Вы успешно зарегистрировались");
        }
    }

    // Проверка на уникальность логина
    public static boolean isLoginCorrect (String login) {
        if (logins.contains(login)) {
            System.out.println("Логин уже занят. Придумайте другой");
            return false;
        } else {
            return true;
        }
    }

    // Логин
    public static void loginPerson() {
        Scanner sc =  new Scanner(System.in);

        System.out.println("Введите логин");
        String login = sc.nextLine();
        System.out.println("Введите пароль");
        String pass = sc.nextLine();
        Person pers = null;
        if (logins.contains(login)) {

            while (true) {
                pers = PersonCheck(login,pass);
                boolean exitRequest = false;
                if (pers != null) {
                    System.out.println("Авторизация прошла успешно");
                    while (!exitRequest) {
                        menu.showAuthMenu();
                        String answer = sc.nextLine();
                        switch (answer) {

                            case "1" : pers.debet();
                            break;
                            case "2": pers.credit();
                            break;
                            case "3": {
                                System.out.printf("Ваш баланс : %s", pers.getBalance());
                                System.out.println("");
                                break;
                            }
                            case "4" : pers.showTransactionStory();
                            break;
                            case "5": exitRequest = true;
                            break;
                            default: System.out.println("Такой операции не существует");
                        }
                    }
                }
                break;
            }
        } else {
            System.out.println("Такого пользователя не существует");
        }
    }

    // Проверка на совпадение логина и пароля
    public static Person PersonCheck(String log, String pas) {
        for (Person person :
                people) {
            if (person.getLogin().equals(log) && person.getPassword().equals(pas)) {
                return person;
            }
        }
        System.out.println("Неверный логин или пароль");
        return null;
    }


}
