package com.example.androidapp;

import android.content.Intent;
import android.os.Bundle;
import com.example.model.Products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ProductsActivity extends AppCompatActivity {

    private String products_url;
    private List<Products> products;

    public static void display(AppCompatActivity activity, String products_url) {
        Intent intent = new Intent(activity, ProductsActivity.class);
        intent.putExtra("products_url", products_url);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        setTitle("Details catégorie");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        this.products_url = intent.getStringExtra("products_url");

        if(products_url != null) {
            this.getProducts(this.products_url);
        }
    }

    private void getProducts(String products_url) {
        new HttpAsyncTask(products_url, new HttpAsyncTask.HttpAsyncTaskListener() {
            @Override
            public void webServiceDone(String result) {
                try {
                    products = initProducts(result);
                    generateCategoriesListView();
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

    private void generateCategoriesListView() {
        // TODO
    }

    private List<Products> initProducts(String result) throws JSONException {

        JSONArray jsonProducts = new JSONArray(result);
        List<Products> products = new ArrayList<>();

        int nbCategories = jsonProducts.length();
        for(int i = 0; i < nbCategories; i++) {
            Products product = null;
            try {
                JSONObject productsJson = jsonProducts.getJSONObject(i);
                product = new Products(productsJson);
                products.add(product);
            } catch (JSONException e) {}
        }
        return products;
    }

}
