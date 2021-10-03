package com.example.csse_mobileapp;

public class Items {

    private String itemName, price, thresholdUnits, currentUnits;

    public Items() {
    }

    public Items(String itemName, String price, String thresholdUnits, String currentUnits) {
        this.itemName = itemName;
        this.price = price;
        this.thresholdUnits = thresholdUnits;
        this.currentUnits = currentUnits;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getThresholdUnits() {
        return thresholdUnits;
    }

    public void setThresholdUnits(String thresholdUnits) {
        this.thresholdUnits = thresholdUnits;
    }

    public String getCurrentUnits() {
        return currentUnits;
    }

    public void setCurrentUnits(String currentUnits) {
        this.currentUnits = currentUnits;
    }
}
