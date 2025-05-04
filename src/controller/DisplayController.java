package controller;

import java.util.List;
import java.util.Scanner;

import models.Medicine;
import models.User;

public class DisplayController {

    public static final void gotoXY(int x, int y){
        char escCode = 0x1B;
        // int row = 10; int column = 5;
        System.out.print(String.format("%c[%d;%df",escCode,y,x));
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 

    public static void start(){
        Scanner scanner = new Scanner(System.in);
        adminMenuDisplay();
    }

    public static void adminMenuDisplay(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            clearScreen();
            System.out.println("+---Pharmacy Management System---+");
            System.out.println("| 1. Medecines                   |");
            System.out.println("| 2. Sales                       |");
            System.out.println("| 3. Exit                        |");
            System.out.println("| Choose an option:              |");
            System.out.println("+--------------------------------+");
            gotoXY(21, 5);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    medicineMenuDisplay();
                    break;
                case 2:
                    saleMenuDisplay();
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }

    public static void medicineMenuDisplay(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            clearScreen();
            System.out.println("+--------Medicine Menu--------+");
            System.out.println("| 1. Add Medicine             |");
            System.out.println("| 2. View Medicines           |");
            System.out.println("| 3. Edit Medicine            |");   
            System.out.println("| 4. Delete Medicine          |");
            System.out.println("| 5. Exit                     |");
            System.out.println("| Choose an option:           |");
            System.out.println("+-----------------------------+");
            try {
                gotoXY(21, 7);
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        MedicineController.addMedicine();
                        break;
                    case 2:
                        clearScreen();
                        MedicineController.getAllMedicinesFromDB().forEach(System.out::println);
                        System.out.print("\ntype any character to exit: ");
                        scanner.next();
                        break;
                    case 3:
                        clearScreen();
                        MedicineController.updateMedicine();
                        break;
                    case 4:
                        clearScreen();
                        MedicineController.deleteMedicine();
                        break;
                    case 5:
                        return;
                    default:
                        break;
                }
            } catch (Exception e) {
                scanner.nextLine();
            }
        }
    }

    public static void displayOneMedicine(Medicine medicine){
        clearScreen();
        System.out.println("<=====Medicine=====>");
        // System.out.println("1.ID : " + medecine.getId());
        System.out.println("1.Name : " + medicine.getName());
        System.out.println("2.Category : " + medicine.getCategory());
        System.out.println("3.Price : " + medicine.getPrice());
        System.out.println("4.Stock : " + medicine.getStock());
        System.out.println("5.Expiry Date : " + medicine.getExpiryDate());
        System.out.println("<==================>");
    }

    public static void addMedicineDisplay(){
        clearScreen();
        System.out.println("<===Add Medicine===>");
        System.out.println("Name : ");
        System.out.println("Category : ");
        System.out.println("Price : ");
        System.out.println("Stock : ");
        System.out.println("Expiry Date : ");
        System.out.println("<==================>");
    }

    public static void userMenuDisplay(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            clearScreen();
            System.out.println("Title !!");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Edit/Delete User");   
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice;
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
        
                        break;
                    case 2:
                        clearScreen(); 
                        UserController.getAllUsersFromDB().forEach(System.out::println);
                        System.out.print("\ntype any character to exit: ");
                        scanner.next();
                        break;
                    case 3:
                        break;
                    case 4:
                        return;
                    default:
                        break;
                }
            } catch (Exception e) {
                scanner.nextLine();
            }
        }
    }

    public static void displayOneUser(User user){
        clearScreen();
        System.out.println("<=======User=======>");
        System.out.println("Name : " + user.getName());
        System.out.println("Username : " + user.getUsername());
        System.out.println("Password : " + user.getpassword());
        System.out.println("Role : " + user.getrole());
        System.out.println("<==================>");
    }

    public static void addUserDisplay(){
        clearScreen();
        System.out.println("<======SignUp======>");
        System.out.println("Name : ");
        System.out.println("Username : ");
        System.out.println("Password : ");
        System.out.println("<==================>");
    }

    public static void saleMenuDisplay(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            clearScreen();
            System.out.println("+---------Sales Menu---------+");
            System.out.println("| 1. Add Medicine to Cart    |");
            System.out.println("| 2. View sales              |");
            System.out.println("| 3. Delete sales            |");   
            System.out.println("| 4. Exit                    |");
            System.out.println("| Choose an option:          |");
            System.out.println("+----------------------------+");
            gotoXY(21, 6);
            int choice;
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        CartService.manageCartInteraction();
                        break;
                    case 2:
                        clearScreen(); 
                        SalesController.viewAllSales();
                        System.out.print("\ntype any character to exit: ");
                        scanner.next();
                        break;
                    case 3:
                        editDeleteSale();
                        break;
                    case 4:
                        return;
                    default:
                        break;
                }
            } catch (Exception e) {
                scanner.nextLine();
            }
        }
    }

    public static void editDeleteSale(){
            Scanner scanner = new Scanner(System.in);
            int choice = -1; 
        do {
            clearScreen();
            SalesController.viewAllSales();
            System.out.print("\n 1.[Exit] 2.[Delete]\nChoice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (choice) {
                    case 1:
                        return;
                    case 2:
                        SalesController.deleteSale();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                scanner.nextLine();
            }
        } while (choice != 1);
    }


    public static void main(String[] args) {
        start();
    }
}



