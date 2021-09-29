package com.example.csse_mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.csse_mobileapp.Database.DBHelper;

public class VendorDetails extends AppCompatActivity {

    EditText vendorName, vendorNumber, vendorEmail, vendorAddress;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_details);

        vendorName = findViewById(R.id.vname);
        vendorNumber = findViewById(R.id.vnumber);
        vendorEmail = findViewById(R.id.vemail);
        vendorAddress = findViewById(R.id.vaddress);
        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());

                long newVendorID = dbHelper.addVendorInfo(vendorName.getText().toString(),
                        vendorNumber.getText().toString(),
                        vendorEmail.getText().toString(),
                        vendorAddress.getText().toString());

                Toast.makeText(VendorDetails.this, "Details added successfully. ID : " +newVendorID, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), OrderSummary.class);
                startActivity(i);
            }
        });
    }

//    public void sendMessageToSummary(View view) {
//        Intent intent = new Intent(this, OrderSummary.class);
//        startActivity(intent);
//    }
}

