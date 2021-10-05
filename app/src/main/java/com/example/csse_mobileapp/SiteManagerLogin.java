package com.example.csse_mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.csse_mobileapp.Database.DBHelper;

import java.util.List;

public class SiteManagerLogin extends AppCompatActivity {

    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_manager_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

    }
}