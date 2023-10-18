package ru.ylab.in.repository;

import ru.ylab.in.models.Person;
import ru.ylab.in.models.Transactions;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Statements {

    private static Scanner scanner = new Scanner(System.in);
    public static void insertPerson(Connection connection, Person person) throws SQLException {
        String insertDataSQL = "INSERT INTO people (login, password, balance) VALUES (?, ?, ?)";
        PreparedStatement insertDataStatement = connection.prepareStatement(insertDataSQL);
        insertDataStatement.setString(1, person.getLogin());
        insertDataStatement.setString(2, person.getPassword());
        insertDataStatement.setDouble(3, 0.0);
        insertDataStatement.executeUpdate();

    }
    public static boolean isExist(Connection connection, String login) throws SQLException {
        String insertDataSQL = "SELECT EXISTS(SELECT * FROM people WHERE login = '" + login +"')";
        Statement insertDataStatement = connection.createStatement();
        ResultSet resultSet = insertDataStatement.executeQuery(insertDataSQL);
        boolean result = false;
        if (resultSet.next()) {
            result = resultSet.getBoolean("exists");
        }
        if(result) {
            System.out.println("Логин занят другим пользователем");
        }
        return result;
    }

    public static Person personCheck(Connection connection, String login, String password) throws SQLException {
        String query = "SELECT * FROM people WHERE login = ? LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,login);
        ResultSet resultSet = preparedStatement.executeQuery();


        while (resultSet.next()) {
            if (resultSet.getString("password").equals(password)) {
                return new Person(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password"), resultSet.getDouble("balance"));
            } else {
                System.out.println("Пароль неверный");
            }
        }
        return null;
    }

    public static void debit(Connection connection, Person person) throws SQLException {
        System.out.println("Введите сумму снятия");
        double amount = scanner.nextDouble();
        String query = "SELECT * FROM people WHERE login = ? LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,person.getLogin());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        if (resultSet.getDouble("balance") >= amount) {
            String query2 = "UPDATE people SET balance = ? WHERE login = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(query2);
            preparedStatement1.setDouble(1, resultSet.getDouble("balance") - amount);
            preparedStatement1.setString(2, person.getLogin());
            preparedStatement1.executeUpdate();
            System.out.printf("Вы успешно сняли %s.", amount);

            String queryTrans = "INSERT into transactions VALUES (?,?,?,?)";
            PreparedStatement preparedStatementTrans = connection.prepareStatement(queryTrans);
            preparedStatementTrans.setObject(1, UUID.randomUUID());
            preparedStatementTrans.setString(2, "DEBIT");
            preparedStatementTrans.setDouble(3, amount);
            preparedStatementTrans.setDouble(4, person.getId());
            preparedStatementTrans.executeUpdate();

        } else {
            System.out.println("Недостаточно средств");
        }

    }

    public static void credit(Connection connection, Person person) throws SQLException {
        System.out.println("Введите сумму пополнения");
        double amount = scanner.nextDouble();
        String query = "SELECT * FROM people WHERE login = ? LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,person.getLogin());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        String query2 = "UPDATE people SET balance = ? WHERE login = ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(query2);
        preparedStatement1.setDouble(1, resultSet.getDouble("balance") + amount);
        preparedStatement1.setString(2, person.getLogin());
        preparedStatement1.executeUpdate();
        System.out.printf("Вы успешно пополнили %s.", amount);

        String queryTrans = "INSERT into transactions VALUES (?,?,?,?)";
        PreparedStatement preparedStatementTrans = connection.prepareStatement(queryTrans);
        preparedStatementTrans.setObject(1, UUID.randomUUID());
        preparedStatementTrans.setString(2, "CREDIT");
        preparedStatementTrans.setDouble(3, amount);
        preparedStatementTrans.setDouble(4, person.getId());
        preparedStatementTrans.executeUpdate();
    }

    public static void showTrans(Connection connection, Person person) throws SQLException {
        String queryTrans = "SELECT * from transactions WHERE person_id = ?";
        PreparedStatement preparedStatementTrans = connection.prepareStatement(queryTrans);
        preparedStatementTrans.setInt(1, person.getId());
        ResultSet resultSet = preparedStatementTrans.executeQuery();
        ArrayList<Transactions> trans = new ArrayList<>();
        while (resultSet.next()) {
            trans.add(new Transactions(UUID.fromString(resultSet.getString("UUID")),resultSet.getInt("person_id"), resultSet.getString("operation_name"), resultSet.getDouble("amount")));
        }
        System.out.println(trans);
    }


}
