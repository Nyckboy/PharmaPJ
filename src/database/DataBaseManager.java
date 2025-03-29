package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DataBaseManager {
    private static final String URL = "jdbc:sqlite:database/pharmacy.db";

    public static Connection connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    public static void createTables() {
        String createMedicinesTable = """
            CREATE TABLE IF NOT EXISTS medecines (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                category TEXT,
                price REAL NOT NULL CHECK(stock >= 0),
                stock INTEGER NOT NULL CHECK(stock >= 0),
                expiry_date TEXT
            );
            """;
        String createUsersTable = """
            CREATE TABLE IF NOT EXISTS User (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                username TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL,
                role TEXT CHECK(role IN ('admin', 'client')) NOT NULL
            );    
            """;
        String createSalesTable = """
            CREATE TABLE IF NOT EXISTS Sale (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id INTEGER,
                medecine_id TEXT NOT NULL,
                quantity_sold TEXT NOT NULL,
                sale_date TEXT DEFAULT CURRENT_TIMESTAMP,
                total_price REAL,
                FOREIGN KEY (user_id) REFERENCES User(id),
                FOREIGN KEY (medecine_id) REFERENCES medecines(id)
            );    
            """;

        try (Connection conn = connect();
            Statement stmt = conn.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createMedicinesTable);
            stmt.execute(createSalesTable);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.out.println("Table creation failed: " + e.getMessage());
        }
    }


    // public void test()

    public static void main(String[] args) {
        // Connection conn = DataBaseManager.connect();
        DataBaseManager.createTables();
    }
}
