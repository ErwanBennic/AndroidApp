package com.example.androidapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends  AppCompatActivity {

    public static void display(AppCompatActivity activity, String products_url) {
        Intent intent = new Intent(activity, ProductsActivity.class);
        intent.putExtra("products_url", products_url);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        setTitle("Produit Détail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        //this.getProductList(produit);


    }
}
