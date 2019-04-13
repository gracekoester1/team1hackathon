package com.example.gracekoester.connectfirebasetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;   //initializes firebase
    EditText editTextEmail, editTextPassword;
    ProgressBar progressBar;
    Button goToMainPage;
    MainActivity myself;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.myself = this;

        mAuth = FirebaseAuth.getInstance();      //checks if user is signed in or out

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        findViewById(R.id.signupTextView).setOnClickListener(this);
        findViewById(R.id.loginTextView).setOnClickListener(this);

    }



    //private methods are only accessible within this class
    private void userLogin() {             //checks to see if email & password fit requirements
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        progressBar.setVisibility(View.INVISIBLE);

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 8) {
            editTextPassword.setError("Please enter 8 characters");
            editTextPassword.requestFocus();
            return;
        }




        //from firebase tutorial, takes in 2 parameters, listener takes in an interface object
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                   Intent intent = new Intent(myself, homeScreen.class);
                   //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    //void returns nothing, just performs functions, protected pretty much means private here
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, homeScreen.class));
        }
    }

    //public can be accessed by other classes
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signupTextView:
                finish();
                startActivity(new Intent(this, signupPage.class));
                break;

            case R.id.loginTextView:
                userLogin();
                break;
        }
    }
}