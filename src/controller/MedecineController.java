package controller;

import database.DataBaseManager;
import models.Medecine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// import controller.DisplayController;

public class MedecineController {

    private static void addMedecineToDB(Medecine medecine) {
        String sqlQuery = "INSERT INTO medecines (name, category, price, stock, expiry_date) values (?,?,?,?,?)";
        try(Connection conn = DataBaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
            pstmt.setString(1, medecine.getName());
            pstmt.setString(2, medecine.getCategory());
            pstmt.setDouble(3, medecine.getPrice());
            pstmt.setInt(4, medecine.getStock());
            pstmt.setString(5, medecine.getExpiryDate());
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
            addMedecineToDB(medecine);
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

    private static void updateMedecineToDB(Medecine toModify){
        String sqlQuery = "UPDATE medecines set name = ?, category = ?, price = ?, stock = ?, expiry_date = ? where id = ?";
        try(Connection conn = DataBaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
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

    public static void updateMedecine(){
        Scanner scanner = new Scanner(System.in);
        List<Medecine> medecines =  MedecineController.getAllMedecinesFromDB();
        medecines.forEach(System.out::println);
        System.out.print("\ntype the ID of the Medecine to Update: ");
        int choice = scanner.nextInt();
        medecines.forEach(medecine -> {
            if(medecine.getId() == choice){
                while (true) {
                    DisplayController.displayOneMedecine(medecine);
                    System.out.println("6.Exit: ");
                    System.out.print("choose an option to update : ");
                    int Choice  = scanner.nextInt();
                    scanner.nextLine();
                    switch (Choice) {
                        case 1:
                        System.out.print("Name : ");
                        medecine.setName(scanner.nextLine());
                        break;
                        case 2:
                        System.out.print("Category : ");
                        medecine.setCategory(scanner.nextLine());
                        break;
                        case 3:
                        System.out.print("Price : ");
                        medecine.setPrice(scanner.nextDouble());
                        break;
                        case 4:
                        System.out.print("Stock : ");
                        medecine.setStock(scanner.nextInt());
                        break;
                        case 5:
                        System.out.print("Expiry Date : ");
                        medecine.setExpiryDate(scanner.nextLine());
                        break;
                        case 6:
                        updateMedecineToDB(medecine);
                        return;
                    }
                }
            }
        });
    }

    private static void deleteMedecineFromDB(Medecine medecine) {
        Scanner scanner = new Scanner(System.in);
        String SqlQuery = "DELETE FROM medecines WHERE id = ?";
        try(Connection conn = DataBaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(SqlQuery)) {
            pstmt.setInt(1, medecine.getId());
            pstmt.executeUpdate();
            System.out.println("Medecine deleted successfully.");
                scanner.nextLine();
            } catch (SQLException e) {
                System.out.println("Error deleting Medecine: " + e.getMessage());
                scanner.nextLine();
        }
    }

    public static void deleteMedecine() {
        Scanner scanner = new Scanner(System.in);
        List<Medecine> medecines =  MedecineController.getAllMedecinesFromDB();
        if (medecines.isEmpty()) {
            System.out.println("No medicines available.");
            return;
        }
        medecines.forEach(System.out::println);
        System.out.print("\ntype the ID of the Medecine to Delete: ");
        int choice = scanner.nextInt();
        Medecine selectedMedecine = null;
        for (Medecine medecine : medecines) {
            if (medecine.getId() == choice) {
                selectedMedecine = medecine;
                break;
            }
        }
        if (selectedMedecine != null) {
            deleteMedecineFromDB(selectedMedecine);
        } else {
            System.out.println("Invalid ID. No medicine found.");
        }
    }

    public static void main(String[] args) {
        addMedecine();
    }
}