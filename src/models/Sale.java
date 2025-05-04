package models;

public class Sale {
    private int saleId;
    private String buyerName;
    private String saleDate;

    public Sale(int saleId, String buyerName, String saleDate) {
        this.saleId = saleId;
        this.buyerName = buyerName;
        this.saleDate = saleDate;
    }

    public int getSaleId() { return saleId; }
    public String getBuyerName() { return buyerName; }
    public String getSaleDate() { return saleDate; }
}
