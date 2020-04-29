package com.example.androidapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.Products;

public class ProductDetailActivity extends  AppCompatActivity {

    private Products produit;

    public static void display(AppCompatActivity activity,String Nomproduct) {
        Intent intent = new Intent(activity, ProductsActivity.class);
        intent.putExtra("nomproduct",Nomproduct);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        setTitle("Produit Détail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        generateProductDetail();
    }

    private void generateProductDetail(){
        ImageView imageView = findViewById(R.id.imageViewProduct);
        Bitmap bitmap = BitmapFactory.decodeFile(produit.getPicture_url());
        imageView.setImageBitmap(bitmap);

        TextView textViewDescription = findViewById(R.id.textViewDescriptionProduct);
        textViewDescription.setText(produit.getDescription());

    }

    /*private void generateProductsListView() {
        ListView listView = findViewById(R.id.listViewDetailProduct);
        productsAdapter = new ProductListAdapter(this, R.layout.products_cell, produits);
        listView.setAdapter(productsAdapter);
    }*/
}
