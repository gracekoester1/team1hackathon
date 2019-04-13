package com.example.gracekoester.connectfirebasetest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class homeScreen extends AppCompatActivity {

    Button goToProfileButton;
    FirebaseAuth mAuth;
    FirebaseAuth AuthUI;
    homeScreen myself;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mAuth = FirebaseAuth.getInstance();
        AuthUI = FirebaseAuth.getInstance();
        this.myself = this;



        Button goToProfilePage = (Button) findViewById(R.id.goToProfileButton);
        goToProfilePage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), profilePage.class);
                startActivityForResult(myIntent, 0);
            }

        });


        Button logOutButton = (Button) findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AuthUI.getInstance().signOut();
                myself.finish();
            }
        });




    }



    }

