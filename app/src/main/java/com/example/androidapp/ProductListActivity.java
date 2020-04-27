package com.example.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.Products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private ProductListAdapter productsAdapter;
    private List<Products> produits;
    private List<Products> products = new ArrayList<>();


    public static void display(AppCompatActivity activity, String nomproduct) {
        Intent intent = new Intent(activity, ProductListActivity.class);
        intent.putExtra("name", nomproduct);
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
        //List<Products> products = new ArrayList<>();

        int nbProducts = jsonProducts.length();
        for(int i = 0; i < nbProducts; i++) {
            Products product = null;
            try {
                JSONObject productJson = jsonProducts.getJSONObject(i);
                product = new Products(productJson);
                products.add(product);
            } catch (JSONException e) {

            }
        }
        return products;
    }

    private void generateProductsListView() {
        ListView listView = findViewById(R.id.products_listView);
        productsAdapter = new ProductListAdapter(this,R.layout.activity_product_list, (ArrayList<Products>) produits);
        listView.setAdapter(productsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductDetailActivity.display(ProductListActivity.this, produits.get(position).getPictures_url());
            }
        });
    }

    private void getProductList() {
        new HttpAsyncTask("http://djemam.com/epsi/boissons.json", new HttpAsyncTask.HttpAsyncTaskListener() {
            @Override
            public void webServiceDone(String result) {
                try {
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
