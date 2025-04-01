package controller;

import database.DataBaseManager;
import models.Medecine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// import controller.DisplayController;

public class MedecineController {

    private static void addMedecineToDB(String name, String category, double price, int stock, String expiryDate){
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
    
    public static void addMedecine(){
        Medecine medecine = new Medecine(0, null, null, 0, 0, null);
        DisplayController.addMedecineDisplay();
        Scanner scanner = new Scanner(System.in);
        DisplayController.gotoXY(15, 2);
        medecine.setName(scanner.nextLine());
        DisplayController.gotoXY(15, 3);
        medecine.setCategory(scanner.nextLine());
        DisplayController.gotoXY(15, 4);
        medecine.setPrice(scanner.nextDouble());
        DisplayController.gotoXY(15, 5);
        medecine.setStock(scanner.nextInt());
        scanner.nextLine();
        DisplayController.gotoXY(15, 6);
        medecine.setExpiryDate(scanner.nextLine());
        DisplayController.gotoXY(1, 8);
        
        System.out.print("Confirme [Y/N] : ");
        String choice = scanner.nextLine().toLowerCase();
        if (choice.equals("yes") || choice.equals("y")) {
            addMedecineToDB(medecine.getName(), 
                            medecine.getCategory(), 
                            medecine.getPrice(), 
                            medecine.getStock(), 
                            medecine.getExpiryDate());
        }else if (choice.equals("no") || choice.equals("n")) {
            return;
        }
    }

    public static List<Medecine> getAllMedecinesFromDB(){
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
            System.out.println("Failed to fetch medecines: " + e.getMessage());
        }
        return medecines;
    }

    public static void updateMedecineToDB(Medecine toModify){
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
            System.out.println("Failed to update medecine: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        addMedecine();
    }
}