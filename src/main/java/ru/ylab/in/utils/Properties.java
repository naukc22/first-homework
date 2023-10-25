package ru.ylab.in.utils;

public class Properties {
    private static String path = "jdbc:postgresql://localhost:5432/bank";
    private static String login = "postgres";
    private static String password = "123";

    public static String getPath() {
        return path;
    }

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }
}
