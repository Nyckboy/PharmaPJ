package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.*;

import database.DataBaseManager;

public class SalesController {

    public static int createSale(String buyerName, String saleDate) {
        int saleId = -1;
        try (Connection conn = DataBaseManager.connect()) {
            String sql = "INSERT INTO Sales (buyer_name, sale_date) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, buyerName);
            stmt.setString(2, saleDate);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                saleId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error creating sale: " + e.getMessage());
        }
        return saleId;
    }

    public static void addSaleItems(int saleId, List<CartItem> items) {
        try (Connection conn = DataBaseManager.connect()) {
            String sql = "INSERT INTO SaleItems (sale_id, medicine_id, quantity, price) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (CartItem item : items) {
                stmt.setInt(1, saleId);
                stmt.setInt(2, item.getMedicineId());
                stmt.setInt(3, item.getQuantity());
                stmt.setDouble(4, item.getPrice());
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            System.out.println("Error adding sale items: " + e.getMessage());
        }
    }

    public static List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM Sales ORDER BY sale_id DESC";
        try(Connection conn = DataBaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();){
                while (rs.next()) {
                    sales.add(new Sale(
                        rs.getInt("sale_id"),
                        rs.getString("buyer_name"),
                        rs.getString("sale_date")
                    ));
                }
        } catch (Exception e) {
            System.out.println("Error retrieving sales: " + e.getMessage());
        }
        return sales;
    }

    public static List<SaleItem> getItemsForSale(int saleId) {
        List<SaleItem> items = new ArrayList<>();
        String sql = """
            SELECT si.medicine_id, m.name, si.quantity, si.price
            FROM SaleItems si
            JOIN Medicines m ON m.id = si.medicine_id
            WHERE si.sale_id = ?
        """;
        try (Connection conn = DataBaseManager.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, saleId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                items.add(new SaleItem(
                    rs.getInt("medicine_id"),
                    rs.getString("name"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving sale items: " + e.getMessage());
        }
        return items;
    }

    public static void viewAllSales() {
        List<Sale> sales = getAllSales();
        if (sales.isEmpty()) {
            System.out.println("No sales found.");
            return;
        }
    
        for (Sale sale : sales) {
            System.out.println(" Sale ID: " + sale.getSaleId() +
                               " | Buyer: " + sale.getBuyerName() +
                               " | Date: " + sale.getSaleDate());
            List<SaleItem> items = getItemsForSale(sale.getSaleId());
            double total = 0;
            for (SaleItem item : items) {
                System.out.println("    @ " + item.getMedicineName() +
                                   " | Qty: " + item.getQuantity() +
                                   " | Unit: " + item.getPrice() +
                                   " | Total: " + item.getTotal());
                total += item.getTotal();
            }
            System.out.println("   >> Sale Total: " + total + " MAD\n");
        }
    }

    public static boolean deleteSale(int saleId) {
        try (Connection conn = DataBaseManager.connect()) {
            String sql = "DELETE FROM Sales WHERE sale_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, saleId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting sale: " + e.getMessage());
            return false;
        }
    }
    
    public static void deleteSale() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Sale ID to delete: ");
        int saleId = scanner.nextInt();
        scanner.nextLine();

        boolean success = deleteSale(saleId);
        if (success) {
            System.out.println("Sale " + saleId + " deleted successfully.");
            scanner.nextLine();
        } else {
            System.out.println("Failed to delete sale. ID may not exist.");
            scanner.nextLine();
        }
    }

}

