package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity {

    Button Go,LogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Go = findViewById(R.id.Go);
        LogIn = findViewById(R.id.LogIn);

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu();
            }
        });

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });
    }
    public void openMenu(){
        Intent intent = new Intent(this,dashboard.class);
        startActivity(intent);
    }

    public void openLogin(){
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
    }
}