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

public class RegisterNewUser extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void registerUser(View view) {
        String email = ((EditText) findViewById(R.id.emailET)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordET)).getText().toString();
        if (isDataValidate(email, password)) {
            registerUser(email, password);
        } else {
            Toast.makeText(this, "Some data is missing....", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isDataValidate(String email, String password) {
        return email.length() > 0 && password.length() > 0;
    }

    private void registerUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterNewUser.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                            firebaseAuth.signOut();
                            startActivity(new Intent(RegisterNewUser.this, MainActivity.class));
                        } else {
                            Toast.makeText(RegisterNewUser.this, "Error while Registering: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
