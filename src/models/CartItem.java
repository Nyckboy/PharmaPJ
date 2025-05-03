package models;

public class CartItem {
    private int medicineId;
    private String medicineName;
    private int quantity;
    private double price;

    public CartItem(int medicineId, String medicineName, int quantity, double price) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getMedicineId() { return medicineId; }
    public String getMedicineName() { return medicineName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public double getTotal() { return price * quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return medicineName + " | Qty: " + quantity + " | Price: " + price + " | Total: " + getTotal();
    }
}
