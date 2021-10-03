package com.example.csse_mobileapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SiteDetails extends AppCompatActivity {

    private EditText siteManagerName, department, vendorType, deliveryDate, deliveryLocation, status, orderDate, number, email, poNumber;
    private Button next;
    private ProgressDialog loadingBar;

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
        poNumber = findViewById(R.id.poNumber);
        next = findViewById(R.id.save);
        loadingBar = new ProgressDialog(this);

//        next.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                DBHelper dbHelper = new DBHelper(getApplicationContext());
////                long newID = dbHelper.addSiteInfo(siteManagerName.getText().toString(),
////                        department.getText().toString(),
////                        vendorType.getText().toString(),
////                        deliveryDate.getText().toString(),
////                        deliveryLocation.getText().toString(),
////                        status.getText().toString(),
////                        orderDate.getText().toString(),
////                        number.getText().toString(),
////                        email.getText().toString());
//////                        poNumber.getText().toString());
////
////                Toast.makeText(SiteDetails.this, "Details added successfully. Order ID : "+newID, Toast.LENGTH_SHORT).show();
////
////                Intent i = new Intent(getApplicationContext(), VendorDetails.class);
////                startActivity(i);
////            }
////        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSiteDetail();
            }
        });

    }

    private void AddSiteDetail() {

        String po = poNumber.getText().toString();
        String name = siteManagerName.getText().toString();
        String departmentName = department.getText().toString();
        String vendorT = vendorType.getText().toString();
        String dDate = deliveryDate.getText().toString();
        String dLocation = deliveryLocation.getText().toString();
        String  oDate = orderDate.getText().toString();
        String phone = number.getText().toString();
        String sEmail = email.getText().toString();

        if(TextUtils.isEmpty(po)){
            Toast.makeText(this, "Please enter the PO Number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please enter the name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(departmentName)){
            Toast.makeText(this, "Please enter the department name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(vendorT)){
            Toast.makeText(this, "Please enter the vendorT", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(dDate)){
            Toast.makeText(this, "Please enter the delivery date", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(dLocation)){
            Toast.makeText(this, "Please enter the delivery location", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(oDate)){
            Toast.makeText(this, "Please enter the order date", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please enter the phone number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(sEmail)){
            Toast.makeText(this, "Please enter the email", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Added site details");
            loadingBar.setMessage("Please wait for a moment");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatePONumber(po,name,departmentName,vendorT,dDate,dLocation,oDate,phone,sEmail);
        }
    }

    private void ValidatePONumber(String po, String name, String departmentName, String vendorT, String dDate, String dLocation, String oDate, String phone, String sEmail) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(!(snapshot.child("Site").child(po).exists())){
                    HashMap<String, Object> siteDataMap = new HashMap<>();
                    siteDataMap.put("po", po);
                    siteDataMap.put("name", name);
                    siteDataMap.put("departmentName", departmentName);
                    siteDataMap.put("vendorT", vendorT);
                    siteDataMap.put("dDate", dDate);
                    siteDataMap.put("dLocation", dLocation);
                    siteDataMap.put("oDate", oDate);
                    siteDataMap.put("phone", phone);
                    siteDataMap.put("sEmail", sEmail);

                    RootRef.child("Site").child(po).updateChildren(siteDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SiteDetails.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent i = new Intent(SiteDetails.this, VendorDetails.class);
                                        startActivity(i);
                                    }
                                    else{
                                        loadingBar.dismiss();
                                        Toast.makeText(SiteDetails.this, "Network Error", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            });

                }
                else {
                    Toast.makeText(SiteDetails.this, "PO number already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(SiteDetails.this, "Add another PO number", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(SiteDetails.this, ViewStock.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}