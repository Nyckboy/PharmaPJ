package controller;

import java.util.Scanner;

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
            System.out.println("\nPharmacy Management System");
            System.out.println("1. Medecines");
            System.out.println("2. Users");
            System.out.println("3. Sales");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    medecineDisplay();
                    break;
                case 2:
                    userDisplay();
                    break;
                case 3:
                    break;
                case 4:
                    return;
                default:
                    break;
            }
        }
    }

    public static void medecineDisplay(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            clearScreen();
            System.out.println("Title !!");
            System.out.println("1. Add Medicine");
            System.out.println("2. View Medicines");
            System.out.println("3. Edit all Medecine");   
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    MedecineController.addMedecine();
                    break;
                case 2:
                    clearScreen();
                    MedecineController.getAllMedecinesFromDB().forEach(System.out::println);
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
        }
    }

    public static void userDisplay(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            clearScreen();
            System.out.println("Title !!");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Edit all User");   
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
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
        }
    }

    public static void addMedecineDisplay(){
        clearScreen();
        System.out.println("<===Add Medecine===>");
        System.out.println("Name : ");
        System.out.println("Category : ");
        System.out.println("Price : ");
        System.out.println("Stock : ");
        System.out.println("Expiry Date : ");
        System.out.println("<==================>");
    }

    // public static void main(String[] args) {
    //     clearScreen();
    //     gotoXY(10, 5);
    //     System.out.print("test");
    // }
}



