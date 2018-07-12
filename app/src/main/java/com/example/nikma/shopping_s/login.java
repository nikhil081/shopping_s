package com.example.nikma.shopping_s;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class login extends AppCompatActivity {
    List<AuthUI.IdpConfig> providers;
    private static final int RC_SIGN_IN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() !=null){
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        } else {
            providers = new ArrayList<>();
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build()))
            .build(),RC_SIGN_IN);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RC_SIGN_IN){
            if (resultCode==RESULT_OK){
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        }
    }
}
