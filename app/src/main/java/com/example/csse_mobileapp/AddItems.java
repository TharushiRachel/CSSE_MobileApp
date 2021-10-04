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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddItems extends AppCompatActivity {

    EditText itemName, unitPrice, thresholdUnits, currentUnits;
    Button save, newRequisition;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        itemName = findViewById(R.id.itemName);
        unitPrice = findViewById(R.id.unitPrice);
        thresholdUnits = findViewById(R.id.thresholdUnit);
        currentUnits = findViewById(R.id.currentUnits);
        save = findViewById(R.id.saveItem);
        newRequisition = (Button) findViewById(R.id.createRequisition);
        loadingBar = new ProgressDialog(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddItemDetail();
            }
        });

        newRequisition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewRequisition();
            }
        });

    }

    private void createNewRequisition() {
        Intent intent = new Intent(this, NewRequisition.class);
        startActivity(intent);
    }

    private void AddItemDetail() {

        String name = itemName.getText().toString();
        String price = unitPrice.getText().toString();
        String threshold_units = thresholdUnits.getText().toString();
        String current_units = currentUnits.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please enter the name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(price)){
            Toast.makeText(this, "Please enter the price", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(threshold_units)){
            Toast.makeText(this, "Please enter the threshold units", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(price)){
            Toast.makeText(this, "Please enter the current units", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Added Item details");
            loadingBar.setMessage("Please wait for a moment");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidateItemName(name, price, threshold_units, current_units);
        }
    }

    private void ValidateItemName(String name, String price, String threshold_units, String current_units) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Item").child(name).exists())){
                    HashMap<String, Object> itemDataMap = new HashMap<>();
                    itemDataMap.put("itemName", name);
                    itemDataMap.put("price", price);
                    itemDataMap.put("thresholdUnits", threshold_units);
                    itemDataMap.put("currentUnits", current_units);

                    RootRef.child("Item").child(name).updateChildren(itemDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(AddItems.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent i = new Intent(AddItems.this, ViewStock.class);
                                        startActivity(i);
                                    }
                                    else{
                                        loadingBar.dismiss();
                                        Toast.makeText(AddItems.this, "Network Error", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            });
                }

                else {
                    Toast.makeText(AddItems.this, "Item Name number already exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(AddItems.this, "Add another Name number", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(AddItems.this, ViewStock.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}