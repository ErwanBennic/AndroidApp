package com.example.androidapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000; // Splash duration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Change intent after splash timeout
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent HomeIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(HomeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
