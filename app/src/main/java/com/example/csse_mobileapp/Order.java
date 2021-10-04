package com.example.csse_mobileapp;

public class Order {

    private String ItemName;
    private double UnitPrice, quantity;
    private double total;

    public Order() {
    }

    public Order(String itemName, double unitPrice, double quantity) {
        ItemName = itemName;
        UnitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotal(){
        total = total + UnitPrice*quantity;
        return total;
    }
}
