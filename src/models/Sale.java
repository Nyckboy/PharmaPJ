package models;

public class Sale {
    private int id;
    private int userId;
    private String medecineId;
    private String quantitySold;
    private String saleDate;
    private double totalPrice;

    public Sale(int id, int userId, String medecineId, String quantitySold, String saleDate, double totalPrice){
        this.id = id;
        this.userId = userId;
        this.medecineId = medecineId;
        this.quantitySold = quantitySold;
        this.saleDate = saleDate;
        this.totalPrice = totalPrice;
    }

    public int getId(){return id;}
    public int getUserIs(){return userId;}
    public String getMedecineId(){return medecineId;}
    public String getQuantitySold(){return quantitySold;}
    public String getSaleDate(){return saleDate;}
    public double getTotalPrice(){return totalPrice;}
    
    public void setUserId(int userId){this.userId = userId;}
    public void setMedecineID(String medecineId){this.medecineId = medecineId;}
    public void setQuantitySold(String quantitySold){this.quantitySold = quantitySold;}
    public void setSaleDate(String saleDate){this.saleDate = saleDate;}
    public void setTotalPrice(double totalPrice){this.totalPrice = totalPrice;}    
}
