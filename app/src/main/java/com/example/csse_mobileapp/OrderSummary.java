package com.example.csse_mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csse_mobileapp.Database.DBHelper;

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
        search = findViewById(R.id.search);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper dbHelper = new DBHelper(getApplicationContext());
                List site = dbHelper.readSiteInfo(siteManagerName.getText().toString());

                if(site.isEmpty()){
                    Toast.makeText(OrderSummary.this, "Didn't included the site details", Toast.LENGTH_SHORT).show();
                    siteManagerName.setText(null);
                }
                else {
                    Toast.makeText(OrderSummary.this, "The details are ", Toast.LENGTH_SHORT).show();
//                    sitePONumber.setText(site.get(0).toString());
                    siteManagerName.setText(site.get(0).toString());
                    sitePhone.setText(site.get(1).toString());
                    siteEmail.setText(site.get(2).toString());
                }
            }
        });
    }


}