package com.example.androidapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

class CategoryDetailsActivity extends AppCompatActivity {

    public static void display(CategoriesActivity categoriesActivity, String products_url) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        setTitle("Details catégorie");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void getDetails(String product_url) {
        new HttpAsyncTask(product_url, new HttpAsyncTask.HttpAsyncTaskListener() {
            @Override
            public void webServiceDone(String result) {
                //TODO
            }

            @Override
            public void webServiceError(Exception e) {
                //TODO
            }
        }).execute();
    }
}
