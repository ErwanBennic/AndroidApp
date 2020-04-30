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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductDetailActivity extends  AppCompatActivity {

    private Products produit;

    public static void display(AppCompatActivity activity, String products) {
        Intent intent = new Intent(activity, ProductDetailActivity.class);
        intent.putExtra("products", products);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        setTitle("Produit Détail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        try {
            JSONObject jsonProducts = new JSONObject(intent.getStringExtra("products"));
            produit = new Products(jsonProducts);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        generateProductDetail();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void generateProductDetail(){
        ImageView imageView = findViewById(R.id.imageViewProduct);
        Picasso.get().load(produit.getPicture_url()).into(imageView);

        TextView textViewDescription = findViewById(R.id.textViewDescriptionProduct);
        textViewDescription.setText(produit.getDescription());
        setTitle(produit.getName());
    }
}
