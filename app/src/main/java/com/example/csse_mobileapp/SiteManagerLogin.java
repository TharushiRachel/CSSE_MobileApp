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

//        DBHelper dbHelper = new DBHelper(this);
//        long val = dbHelper.addInfo("sm@gmail.com","sm");

    }

//    public void signIn(View view){
//        DBHelper dbHelper = new DBHelper(this);
//
//        Intent intent = new Intent(this,ViewStock.class);
//        Intent intent2 = new Intent(this,SiteManagerLogin.class);
//
//        List emails = dbHelper.readAllInfo("email");
//        List passwords = dbHelper.readAllInfo("password");
//
//        String email1 = email.getText().toString();
//        String password1 = password.getText().toString();
//
//        if (emails.indexOf(email1)>=0){
//            if (passwords.get(emails.indexOf(email1)).equals(password1)){
//                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//            }
//            else{
//                Toast.makeText(this, "Login unsuccessful", Toast.LENGTH_SHORT).show();
//                startActivity(intent2);
//            }
//        }
//    }
//
//    public void sendMessage(View view) {
//        Intent intent = new Intent(this, ViewStock.class);
//        startActivity(intent);
//    }
}