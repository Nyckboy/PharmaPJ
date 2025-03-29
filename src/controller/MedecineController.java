package controller;

import database.DataBaseManager;
import models.Medecine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedecineController {

    public static void addMedecine(String name, String category, double price, int stock, String expiryDate){
        String sqlQuerry = "INSERT INTO medecines (name, category, price, stock, expiry_date) values (?,?,?,?,?)";
        try(Connection conn = DataBaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuerry)) {
            pstmt.setString(1, name);
            pstmt.setString(2, category);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, stock);
            pstmt.setString(5, expiryDate);
            pstmt.executeUpdate();
            System.out.println("Medicine added successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to add medecine: " + e.getMessage());
        }
    }    

    public static List<Medecine> getAllMedecines(){
        List<Medecine> medecines = new ArrayList<>();
        String sqlQuerry = "SELECT * FROM medecines";

        try(Connection conn = DataBaseManager.connect();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sqlQuerry)) {
            while (result.next())   {
                medecines.add(new Medecine( result.getInt("id"), 
                                            result.getString("name"),
                                            result.getString("category"),
                                            result.getDouble("price"),
                                            result.getInt("stock"),
                                            result.getString("expiry_date"))); 
                                    }
                                        
        } catch (SQLException e) {
            System.out.println("Failed to fetch medicines: " + e.getMessage());
        }
        return medecines;
    }

    public static void updateMedecine(Medecine toModify){
        String sqlQuerry = "UPDATE medecines set name = ?, category = ?, price = ?, stock = ?, expiry_date = ? where id = ?";
        try(Connection conn = DataBaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuerry)) {
            pstmt.setString(1, toModify.getName());
            pstmt.setString(2, toModify.getCategory());
            pstmt.setDouble(3, toModify.getPrice());
            pstmt.setInt(4, toModify.getStock());
            pstmt.setString(5, toModify.getExpiryDate());
            pstmt.setInt(6, toModify.getId());
            pstmt.executeUpdate();
            System.out.println("User updated successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to update user: " + e.getMessage());
        }
    }
}