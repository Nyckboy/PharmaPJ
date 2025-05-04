package models;

public class SaleItem {
    private int medicineId;
    private String medicineName;
    private int quantity;
    private double price;

    public SaleItem(int medicineId, String medicineName, int quantity, double price) {
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
}

