package com.example.csse_mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.csse_mobileapp.Database.DBHelper;

public class SiteDetails extends AppCompatActivity {

    EditText siteManagerName, department, vendorType, deliveryDate, deliveryLocation, status, orderDate, number, email;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_details);

        siteManagerName = findViewById(R.id.name);
        department = findViewById(R.id.department);
        vendorType = findViewById(R.id.vendorT);
        deliveryDate = findViewById(R.id.ddate);
        deliveryLocation = findViewById(R.id.location);
        status = findViewById(R.id.bstatus);
        orderDate = findViewById(R.id.odate);
        number = findViewById(R.id.number);
        email = findViewById(R.id.email);
        next = findViewById(R.id.save);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                long newID = dbHelper.addSiteInfo(siteManagerName.getText().toString(),
                        department.getText().toString(),
                        vendorType.getText().toString(),
                        deliveryDate.getText().toString(),
                        deliveryLocation.getText().toString(),
                        status.getText().toString(),
                        orderDate.getText().toString(),
                        number.getText().toString(),
                        email.getText().toString());

                Toast.makeText(SiteDetails.this, "Details added successfully. Order ID : "+newID, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), VendorDetails.class);
                startActivity(i);
            }
        });

    }

//    public void sendMessageToVendor(View view) {
//        Intent intent = new Intent(this, VendorDetails.class);
//        startActivity(intent);
//    }
}