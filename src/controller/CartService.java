package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.*;

public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();

    public void addToCart(CartItem item) {
        cartItems.add(item);
        System.out.println("Added to cart: " + item.getMedicineName());
    }

    public void showCart() {
        System.out.println("\nCart Contents:");
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        double total = 0;
        for (CartItem item : cartItems) {
            System.out.println(item);
            total += item.getTotal();
        }
        System.out.println(" Total: " + total + " MAD\n");
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }

    public void checkoutCart(CartService cartService) {
        List<CartItem> items = cartService.getCartItems();
        if (items.isEmpty()) {
            System.out.println("Cart is empty. Cannot proceed to checkout.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter buyer name: ");
        String buyer = scanner.nextLine();
        String saleDate = LocalDate.now().toString();

        int saleId = SalesController.createSale(buyer, saleDate);
        if (saleId != -1) {
            SalesController.addSaleItems(saleId, items);
            System.out.println("Sale completed successfully! Sale ID: " + saleId);
            cartService.clearCart();
        } else {
            System.out.println("Failed to complete sale.");
        }
    }

    public static void manageCartInteraction(){
        DisplayController.clearScreen();
        CartService cartService = new CartService();

        List<Medicine> medicines = MedicineController.getAllMedicinesFromDB();

        Scanner scanner = new Scanner(System.in);
        do {
            DisplayController.clearScreen();
            System.out.println("Available Medicines:");
            for (Medicine m : medicines) {
                System.out.println(m.getId() + " - " + m.getName() + " - " + m.getPrice() + " MAD");
            }
            System.out.println("0.[Exit] -1.[view Cart]");
            System.out.print("Enter Medicine ID to add to cart: ");
            int id = scanner.nextInt();
            scanner.nextLine(); 
    
            if (id == 0) {
                break;
            }
            if (id == -1) {
                int choice = -1;
                do {
                    DisplayController.clearScreen();
                    cartService.showCart();
                    System.out.println("1.[Exit] 2.[CheckOut]");
                    choice = scanner.nextInt();
                    if (choice == 1) {
                        break;
                    }
                    if (choice == 2) {
                        cartService.checkoutCart(cartService);
                        choice = 1;
                    }
                } while (choice != 1);
                continue;
            }

            System.out.print("Enter quantity: ");
            int qty = scanner.nextInt();
            scanner.nextLine();
    
            Medicine selected = medicines.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    
            if (selected != null) {
                CartItem item = new CartItem(selected.getId(), selected.getName(), qty, selected.getPrice());
                cartService.addToCart(item);
            } else {
                System.out.println("Medicine not found.");
            }
        } while (true);

    }

    public static void main(String[] args) {
        
    }
}
