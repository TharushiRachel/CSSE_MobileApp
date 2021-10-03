package com.example.csse_mobileapp;

public class Items {

    private String name, price, threshold_units, current_units;

    public Items() {
    }

    public Items(String name, String price, String threshold_units, String current_units) {
        this.name = name;
        this.price = price;
        this.threshold_units = threshold_units;
        this.current_units = current_units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getThreshold_units() {
        return threshold_units;
    }

    public void setThreshold_units(String threshold_units) {
        this.threshold_units = threshold_units;
    }

    public String getCurrent_units() {
        return current_units;
    }

    public void setCurrent_units(String current_units) {
        this.current_units = current_units;
    }
}
