package com.example.csse_mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.csse_mobileapp.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewStock extends AppCompatActivity {

//    TextView itemName, unitPrice, thresholdUnits, currentUnits;

    CheckBox action;
    ListView myListView;
    DatabaseReference databaseReference;
    List<Items> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stock);



        action = findViewById(R.id.action_chkb);

        myListView = findViewById(R.id.myListView);
//        itemListView = findViewById(R.id.myItemView);
        itemsList = new ArrayList<>();


        databaseReference = FirebaseDatabase.getInstance().getReference("Item");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemsList.clear();

                for(DataSnapshot itemDatasnap : snapshot.getChildren()){
                    Items items = itemDatasnap.getValue(Items.class);
                    itemsList.add(items);
                }

                ListAdapter adapter = new ListAdapter(ViewStock.this, itemsList);
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        itemInfoDisplay(itemNameS, quantityS, unitPriceS);

    }

//    private void itemInfoDisplay(TextView itemNameS, TextView quantityS, TextView unitPriceS) {
//        DatabaseReference ItemRef = FirebaseDatabase.getInstance().getReference("Item");
//
//        ItemRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    if(snapshot.child("itemName").exists()){
//                        if(action.isChecked()){
//                            String itemName = snapshot.child("itemName").getValue().toString();
//                            String unitPrice = snapshot.child("price").getValue().toString();
//
//                            itemNameS.setText(itemName);
//                            unitPriceS.setText(unitPrice);
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }

//    private void itemInfoDisplay(TextView itemName, TextView unitPrice, TextView thresholdUnits, TextView currentUnits) {
//
//        DatabaseReference ItemRef = FirebaseDatabase.getInstance().getReference("Item");
//
//        ItemRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    if(snapshot.child("itemName").exists()){
//                        String itemName1 = snapshot.child("itemName").getValue().toString();
//                        String unitPrice1 = snapshot.child("price").getValue().toString();
//                        String thresholdUnits1 = snapshot.child("thresholdUnits").getValue().toString();
//                        String currentUnits1 = snapshot.child("currentUnits").getValue().toString();
//
//                        itemName.setText(itemName1);
//                        unitPrice.setText(unitPrice1);
//                        thresholdUnits.setText(thresholdUnits1);
//                        currentUnits.setText(currentUnits1);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    public void sendMessage1(View view) {
        Intent intent = new Intent(this, AddOrder.class);
        startActivity(intent);
    }

    public void sendMessage2(View view) {
        Intent intent = new Intent(this, AddItems.class);
        startActivity(intent);
    }
}