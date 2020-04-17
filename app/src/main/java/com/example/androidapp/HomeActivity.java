package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("EPSI");
    }

    public void goToGroups(View view) {
        Intent intent = new Intent(HomeActivity.this, GroupActivity.class);
        startActivity(intent);
    }
}
