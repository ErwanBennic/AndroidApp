package com.example.androidapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.Category;
import com.example.model.Products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private ProductListAdapter productsAdapter;
    private List<Products> produits;

    public static void display(AppCompatActivity activity, String urlProduct) {
        Intent intent = new Intent(activity, ProductListActivity.class);
        intent.putExtra("url", urlProduct);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        setTitle("Produits Liste");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getProductList();
    }

    private List<Products> initProducts(String result) throws JSONException {

        JSONArray jsonProducts = new JSONArray(result);
        List<Products> products = new ArrayList<>();

        int nbProducts = jsonProducts.length();
        for(int i = 0; i < nbProducts; i++) {
            Products product = null;
            try {
                JSONObject productJson = jsonProducts.getJSONObject(i);
                product = new Products(productJson);
                products.add(product);
            } catch (JSONException e) {
                System.out.println("Erreur");
            }
        }
        return products;
    }

    private void generateProductsListView() {
        ListView listView = findViewById(R.id.products_listView);
        productsAdapter = new ProductListAdapter(this, R.layout.products_cell, produits);
        listView.setAdapter(productsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductDetailActivity.display(ProductListActivity.this, produits.get(position).getJson());
            }
        });
    }


    private void getProductList() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        System.out.println(url);
        new HttpAsyncTask(url, new HttpAsyncTask.HttpAsyncTaskListener() {
            @Override
            public void webServiceDone(String result) {
                try {
                    System.out.println(result);
                    produits = initProducts(result);
                    generateProductsListView();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void webServiceError(Exception e) {
                //TODO
            }
        }).execute();
    }


}
