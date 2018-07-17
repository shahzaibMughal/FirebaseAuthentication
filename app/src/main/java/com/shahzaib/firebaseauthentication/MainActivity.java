package com.shahzaib.firebaseauthentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null)
        {
            Toast.makeText(this, "User is Already Logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainSreen.class));
        }
        else
        {
            Toast.makeText(this, "User is NOT Logged in", Toast.LENGTH_SHORT).show();
        }
    }


    public void loginUser(View view) {
        String email = ((EditText)findViewById(R.id.emailET)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordET)).getText().toString();
        if(isDataValidate(email,password))
        {
           loginUser(email,password);
        }
        else
        {
            Toast.makeText(this, "Some data is missing....", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isDataValidate(String email, String password) {
        return email.length()>0 && password.length()>0;
    }

    private void loginUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "User Successfully Logged In ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,MainSreen.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "User NOT Logged In due to some error: "+task.getException(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void registerNewUser(View view) {
        startActivity(new Intent(this,RegisterNewUser.class));
    }
}
