package ru.ylab.in.repository;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import ru.ylab.in.utils.Properties;

import java.sql.Connection;
import java.sql.DriverManager;

public class LiquibaseStart {
    public static void startConnection() {
        try {
            Connection connection = DriverManager.getConnection(
                    Properties.getPath(),
                    Properties.getLogin(),
                    Properties.getPassword()
            );
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase("db/changelog/changelog.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update();
            System.out.println("Миграции успешно выполнены!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
