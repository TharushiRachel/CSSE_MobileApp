package com.example.csse_mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csse_mobileapp.Database.DBHelper;
import com.example.csse_mobileapp.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class OrderSummary extends AppCompatActivity {

    TextView siteDetails;
    EditText siteManagerName, sitePhone, siteEmail,sitePONumber;
    Button search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        siteDetails = findViewById(R.id.stdetails_text);
        siteManagerName = findViewById(R.id.siteManagerName);
        sitePhone = findViewById(R.id.siteNumber);
        siteEmail = findViewById(R.id.siteEmail);
        sitePONumber = findViewById(R.id.poNumber);
//        search = findViewById(R.id.search);


//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                DBHelper dbHelper = new DBHelper(getApplicationContext());
//                List site = dbHelper.readSiteInfo(siteManagerName.getText().toString());
//
//                if(site.isEmpty()){
//                    Toast.makeText(OrderSummary.this, "Didn't included the site details", Toast.LENGTH_SHORT).show();
//                    siteManagerName.setText(null);
//                }
//                else {
//                    Toast.makeText(OrderSummary.this, "The details are ", Toast.LENGTH_SHORT).show();
////                    sitePONumber.setText(site.get(0).toString());
//                    siteManagerName.setText(site.get(0).toString());
//                    sitePhone.setText(site.get(1).toString());
//                    siteEmail.setText(site.get(2).toString());
//                }
//            }
//        });

        siteInfoDisplay(siteManagerName, sitePhone, siteEmail);
    }

    private void siteInfoDisplay( EditText siteManagerName, EditText sitePhone, EditText siteEmail) {

        DatabaseReference SiteRef = FirebaseDatabase.getInstance().getReference().child("Site").child(Prevalent.currentOnlineUser.getPo());

        SiteRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    if(snapshot.child("po").exists()){
                        String name = snapshot.child("name").getValue().toString();
                        String phone = snapshot.child("phone").getValue().toString();
                        String email = snapshot.child("sEmail").getValue().toString();

                        siteManagerName.setText(name);
                        sitePhone.setText(phone);
                        siteEmail.setText(email);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}