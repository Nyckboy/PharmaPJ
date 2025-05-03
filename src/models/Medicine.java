package models;

public class Medicine {
    private int id;
    private String name;
    private String category;
    private double price;
    private int stock;
    private String expiryDate;

    public Medicine(int id, String name, String category, double price, int stock, String expiryDate){
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.expiryDate = expiryDate;
    }

    public int getId(){return id;}
    public String getName(){return name;}
    public String getCategory(){return category;}
    public double getPrice(){return price;}
    public int getStock(){return stock;}
    public String getExpiryDate(){return expiryDate;}

    public void setName(String name){this.name = name; }
    public void setCategory(String category){this.category = category; }
    public void setPrice(double price){this.price = price;}
    public void setStock(int stock){this.stock = stock;}
    public void setExpiryDate(String expiryDate){this.expiryDate = expiryDate;}

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Category: " + category +
                " | Price: " + price + " | Stock: " + stock + " | Expiry: " + expiryDate;
    }
}
