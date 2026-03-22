package uosm.jwt1f24_36400092;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class SetupDB {
    public static void setup() throws SQLException {
        // create database if it doesn't exist, otherwise ignore
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/", "root", "");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS cafe;");
        }
        // create tables if it doesn't exist, otherwise ignore
        try (Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/cafe", "root", "");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS users (
                        userid INT PRIMARY KEY AUTO_INCREMENT,
                        username VARCHAR(50) UNIQUE NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        role VARCHAR(25) NOT NULL DEFAULT 'user',
                        CHECK(role IN ('user','admin')));
                    """);
        }
    }
}
