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
            Statement stmt = conn.createStatement();
            stmt.execute("PRAGMA foreign_keys = ON");
            // System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    public static void createTables() {
        String createMedicinesTable = """
            CREATE TABLE IF NOT EXISTS medicines (
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
            CREATE TABLE Sales (
                sale_id INTEGER PRIMARY KEY AUTOINCREMENT,
                buyer_name TEXT,
                sale_date TEXT
            );    
            """;
        String createSaleItemTable = """
            CREATE TABLE SaleItems (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                sale_id INTEGER,
                medicine_id INTEGER,
                quantity INTEGER,
                price REAL,
                FOREIGN KEY (sale_id) REFERENCES Sales(sale_id) ON DELETE CASCADE
            );   
            """;

        try (Connection conn = connect();
            Statement stmt = conn.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createMedicinesTable);
            stmt.execute(createSalesTable);
            stmt.execute(createSaleItemTable);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.out.println("Table creation failed: " + e.getMessage());
        }
    }


    // public void test()

    public static void main(String[] args) {
        // DataBaseManager.createTables();
        // Connection conn = DataBaseManager.connect();
        // try(Statement stmt = conn.createStatement();){
        //     stmt.execute("DROP TABLE IF EXISTS SaleItems;");
        //     stmt.execute("DROP TABLE IF EXISTS Sales;");
            
        // } catch (SQLException e) {
        //     System.out.println("Error sale: " + e.getMessage());
        // }
        
    }
}
