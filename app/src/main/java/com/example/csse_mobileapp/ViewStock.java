package com.example.csse_mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewStock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stock);
    }

    public void sendMessage1(View view) {
        Intent intent = new Intent(this, PurchaseOrder.class);
        startActivity(intent);
    }
}