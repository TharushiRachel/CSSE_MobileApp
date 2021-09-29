package com.example.csse_mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PurchaseOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_order);
    }

    public void sendMessageToVendor(View view) {
        Intent intent = new Intent(this, VendorDetails.class);
        startActivity(intent);
    }
}