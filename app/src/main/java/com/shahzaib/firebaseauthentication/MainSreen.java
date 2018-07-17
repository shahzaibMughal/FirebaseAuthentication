package com.shahzaib.firebaseauthentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainSreen extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sreen);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void signOutUser(View view) {
        firebaseAuth.signOut();
        Toast.makeText(this, "SignOut successful", Toast.LENGTH_SHORT).show();
        finish();
    }
}
