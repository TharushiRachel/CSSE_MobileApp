package com.example.csse_mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.csse_mobileapp.Database.DBHelper;
import com.example.csse_mobileapp.Database.VendorDBHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class VendorDetails extends AppCompatActivity {

    EditText vendorName, vendorNumber, vendorEmail, vendorAddress;
    Button next;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_details);

        vendorName = findViewById(R.id.vname);
        vendorNumber = findViewById(R.id.vnumber);
        vendorEmail = findViewById(R.id.vemail);
        vendorAddress = findViewById(R.id.vaddress);
        next = findViewById(R.id.next);
        loadingBar = new ProgressDialog(this);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddVendorDetail();
            }
        });
    }

    private void AddVendorDetail() {

        String name = vendorName.getText().toString();
        String number = vendorNumber.getText().toString();
        String email = vendorEmail.getText().toString();
        String address = vendorAddress.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please enter the vendor name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(number)){
            Toast.makeText(this, "Please enter the phone number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter the email", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(address)){
            Toast.makeText(this, "Please enter the address", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Added vendor details");
            loadingBar.setMessage("Please wait for a moment");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatePhone(name,number,email,address);
        }
    }

    private void ValidatePhone(String name, String number, String email, String address) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Vendor").child(number).exists())){
                    HashMap<String, Object> vendorDataMap = new HashMap<>();
                    vendorDataMap.put("name", name);
                    vendorDataMap.put("number", number);
                    vendorDataMap.put("email", email);
                    vendorDataMap.put("address", address);

                    RootRef.child("Vendor").child(number).updateChildren(vendorDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(VendorDetails.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent i = new Intent(VendorDetails.this, ViewStock.class);
                                        startActivity(i);
                                    }
                                    else{
                                        loadingBar.dismiss();
                                        Toast.makeText(VendorDetails.this, "Network Error", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            });

                }

                else {
                    Toast.makeText(VendorDetails.this, "PO number already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(VendorDetails.this, "Add another PO number", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(VendorDetails.this, SiteDetails.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}

