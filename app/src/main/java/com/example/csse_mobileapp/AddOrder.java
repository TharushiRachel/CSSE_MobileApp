package com.example.csse_mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class AddOrder extends AppCompatActivity {

    EditText itemName, unitPrice, quantity, totalP;
    Button calculate, next;
    private ProgressDialog loadingBar;

    private double overTotalPrice=0;

    Context context;
    List<Order> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        itemName = findViewById(R.id.addItemName);
        unitPrice = findViewById(R.id.addUnitPrice);
        quantity = findViewById(R.id.addQuantity);
        totalP = findViewById(R.id.total);
        calculate = findViewById(R.id.calculateBtn);
        next = findViewById(R.id.nextBtn);
        loadingBar = new ProgressDialog(this);



        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddOrderDetails();

//                totalP.setText("Total Price = Rs." +String.valueOf(overTotalPrice));
//                totalP.setText("500");

//                Intent i = new Intent(AddOrder.this, AddOrder.class);
//                i.putExtra("Total Price ", String.valueOf(overTotalPrice));
//                startActivity(i);
//                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddOrder.this, SiteDetails.class);
                startActivity(i);
            }
        });
    }

    private void AddOrderDetails() {

        String name = itemName.getText().toString();
        double price = Double.parseDouble(unitPrice.getText().toString());
        double quantity1 = Double.parseDouble(quantity.getText().toString());


        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please enter the Item name", Toast.LENGTH_SHORT).show();
        }
        else if(unitPrice.length()==0){
            Toast.makeText(this, "Please enter the Unit Price", Toast.LENGTH_SHORT).show();
        }
        else if(quantity.length()==0){
            Toast.makeText(this, "Please enter the quantity", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Added order details");
            loadingBar.setMessage("Please wait for a moment");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidateItemName(name, price, quantity1);
        }
    }

    private void ValidateItemName(String name, double price, double quantity1) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Order").child(name).exists())){
                    HashMap<String, Object> orderDataMap = new HashMap<>();
                    orderDataMap.put("ItemName", name);
                    orderDataMap.put("UnitPrice", price);
                    orderDataMap.put("quantity", quantity1);

                    RootRef.child("Order").child(name).updateChildren(orderDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(AddOrder.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

//                                        Intent i = new Intent(AddOrder.this, AddOrder.class);
//                                        startActivity(i);

                                        totalP.setText("Total Price = Rs.500");

                                        Order order = new Order();
                                        overTotalPrice = overTotalPrice+order.getTotal();

//                                        totalP.setText("Total Price = Rs." +String.valueOf(overTotalPrice));

                                    }
                                    else{
                                        loadingBar.dismiss();
                                        Toast.makeText(AddOrder.this, "Network Error", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            });
                }

//                else {
//                    Toast.makeText(AddOrder.this, "PO number already exists", Toast.LENGTH_SHORT).show();
//                    loadingBar.dismiss();
//                    Toast.makeText(SiteDetails.this, "Add another PO number", Toast.LENGTH_SHORT).show();
//
//                    Intent i = new Intent(SiteDetails.this, ViewStock.class);
//                    startActivity(i);
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}