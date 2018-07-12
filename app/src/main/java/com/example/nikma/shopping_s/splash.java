package com.example.nikma.shopping_s;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.q42.android.scrollingimageview.ScrollingImageView;

public class splash extends AppCompatActivity {
    private static final int SPLASH_TIMEOUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ScrollingImageView scrollingImageView = (ScrollingImageView) findViewById(R.id.scrolling);
        scrollingImageView.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),login.class);
                startActivity(i);
                finish();
            }
        },SPLASH_TIMEOUT);
    }
}
