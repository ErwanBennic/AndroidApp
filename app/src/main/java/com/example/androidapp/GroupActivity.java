package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        setTitle("Infos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void goToEtud1(View view) {
        Intent intent = new Intent(GroupActivity.this, StudentInfoActivity.class);
        startActivity(intent);
    }

    public void goToEtud2(View view) {
        Intent intent = new Intent(GroupActivity.this, StudentInfoActivity2.class);
        startActivity(intent);
    }

    public void goToEtud3(View view) {
        Intent intent = new Intent(GroupActivity.this, StudentInfoActivity3.class);
        startActivity(intent);
    }
}
