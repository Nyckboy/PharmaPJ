package controller;

import database.DataBaseManager;
import models.Medicine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// import controller.DisplayController;

public class MedicineController {

    private static void addMedicineToDB(Medicine medecine) {
        String sqlQuery = "INSERT INTO medicines (name, category, price, stock, expiry_date) values (?,?,?,?,?)";
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
            System.out.println("Failed to add medicine: " + e.getMessage());
        }
    }
    
    public static void addMedicine(){
        Medicine medecine = new Medicine(0, null, null, 0, 0, null);
        DisplayController.addMedicineDisplay();
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
            addMedicineToDB(medecine);
        }else if (choice.equals("no") || choice.equals("n")) {
            return;
        }
    }

    public static List<Medicine> getAllMedicinesFromDB(){
        List<Medicine> medecines = new ArrayList<>();
        String sqlQuerry = "SELECT * FROM medicines";

        try(Connection conn = DataBaseManager.connect();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(sqlQuerry)) {
            while (result.next())   {
                medecines.add(new Medicine( result.getInt("id"), 
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

    private static void updateMedicineToDB(Medicine toModify){
        String sqlQuery = "UPDATE medicines set name = ?, category = ?, price = ?, stock = ?, expiry_date = ? where id = ?";
        try(Connection conn = DataBaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
            pstmt.setString(1, toModify.getName());
            pstmt.setString(2, toModify.getCategory());
            pstmt.setDouble(3, toModify.getPrice());
            pstmt.setInt(4, toModify.getStock());
            pstmt.setString(5, toModify.getExpiryDate());
            pstmt.setInt(6, toModify.getId());
            pstmt.executeUpdate();
            System.out.println("Medicine updated successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to update medecine: " + e.getMessage());
        }
    }

    public static void updateMedicine(){
        Scanner scanner = new Scanner(System.in);
        List<Medicine> medicines =  MedicineController.getAllMedicinesFromDB();
        medicines.forEach(System.out::println);
        System.out.print("\n0.[Exit]");
        System.out.print("\ntype the ID of the Medicine to Update: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 0) {
            return;
        }
        medicines.forEach(medicine -> {
            if(medicine.getId() == choice){
                while (true) {
                    DisplayController.displayOneMedicine(medicine);
                    System.out.println("6.Exit: ");
                    System.out.print("choose an option to update : ");
                    int Choice  = scanner.nextInt();
                    scanner.nextLine();
                    switch (Choice) {
                        case 1:
                        System.out.print("Name : ");
                        medicine.setName(scanner.nextLine());
                        break;
                        case 2:
                        System.out.print("Category : ");
                        medicine.setCategory(scanner.nextLine());
                        break;
                        case 3:
                        System.out.print("Price : ");
                        medicine.setPrice(scanner.nextDouble());
                        break;
                        case 4:
                        System.out.print("Stock : ");
                        medicine.setStock(scanner.nextInt());
                        break;
                        case 5:
                        System.out.print("Expiry Date : ");
                        medicine.setExpiryDate(scanner.nextLine());
                        break;
                        case 6:
                        updateMedicineToDB(medicine);
                        return;
                    }
                }
            }
        });
    }

    private static void deleteMedicineFromDB(Medicine medecine) {
        Scanner scanner = new Scanner(System.in);
        String SqlQuery = "DELETE FROM medicines WHERE id = ?";
        try(Connection conn = DataBaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(SqlQuery)) {
            pstmt.setInt(1, medecine.getId());
            pstmt.executeUpdate();
            System.out.println("Medicine deleted successfully.");
                scanner.nextLine();
            } catch (SQLException e) {
                System.out.println("Error deleting Medicine: " + e.getMessage());
                scanner.nextLine();
        }
    }

    public static void deleteMedicine() {
        Scanner scanner = new Scanner(System.in);
        List<Medicine> medicines =  MedicineController.getAllMedicinesFromDB();
        if (medicines.isEmpty()) {
            System.out.println("No medicines available.");
            return;
        }
        medicines.forEach(System.out::println);
        System.out.print("\n0.[Exit]");
        System.out.print("\ntype the ID of the Medicine to Delete: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 0) {
            return;
        }
        Medicine selectedMedicine = null;
        for (Medicine medicine : medicines) {
            if (medicine.getId() == choice) {
                selectedMedicine = medicine;
                break;
            }
        }
        if (selectedMedicine != null) {
            deleteMedicineFromDB(selectedMedicine);
        } else {
            System.out.println("Invalid ID. No medicine found.");
            scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        addMedicine();
    }
}