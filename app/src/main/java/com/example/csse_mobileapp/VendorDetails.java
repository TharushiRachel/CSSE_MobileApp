package com.example.csse_mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VendorDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_details);
    }

    public void sendMessageToSummary(View view) {
        Intent intent = new Intent(this, OrderSummary.class);
        startActivity(intent);
    }
}

