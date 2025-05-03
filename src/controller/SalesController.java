package controller;

import java.sql.*;
import java.util.List;
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
            System.out.println("❌ Error creating sale: " + e.getMessage());
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
            System.out.println("❌ Error adding sale items: " + e.getMessage());
        }
    }
}

