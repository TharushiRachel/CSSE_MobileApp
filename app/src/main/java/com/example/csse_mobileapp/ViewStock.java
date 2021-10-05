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

    ListView myListView;
    DatabaseReference databaseReference;
    List<Items> itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stock);

        myListView = findViewById(R.id.myListView);
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


    }



    public void sendMessage1(View view) {
        Intent intent = new Intent(this, AddOrder.class);
        startActivity(intent);
    }

    public void sendMessage2(View view) {
        Intent intent = new Intent(this, AddItems.class);
        startActivity(intent);
    }
}