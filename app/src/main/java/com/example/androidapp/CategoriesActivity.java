package com.example.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.model.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {

    private CategoriesAdapter categoriesAdapter;
    private JSONArray jsonCategories;

    public static void display(AppCompatActivity activity) {
        Intent intent = new Intent(activity, CategoriesActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        setTitle("Catégories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //this.getCategories();
        try {
            this.jsonCategories = new JSONArray("[{\"category_id\": \"1\",\"title\": \"Boissons\",\"products_url\": \"http://djemam.com/epsi/boissons.json\"},{\"category_id\": \"2\",\"title\": \"Fromages\",\"products_url\": \"http://djemam.com/epsi/fromages.json\"},{\"category_id\": \"3\",\"title\": \"Surgelés\",\"products_url\": \"http://djemam.com/epsi/surgeles.json\"},{\"category_id\": \"4\",\"title\": \"Sauces\",\"products_url\": \"http://djemam.com/epsi/sauces.json\"}]");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final List<Category> categories = this.initCategories();
        ListView listView = findViewById(R.id.categories_listview);
        categoriesAdapter = new CategoriesAdapter(this,R.layout.categories_cell, categories);
        listView.setAdapter(categoriesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CategoryDetailsActivity.display(CategoriesActivity.this, categories.get(position).getProducts_url());
            }
        });
    }

    private List<Category> initCategories() {
        int nbCategory = this.jsonCategories.length();
        System.out.println(this.jsonCategories.toString());
        List<Category> categories = new ArrayList<>();

        for(int i = 0; i < nbCategory; i++) {
            Category category = null;
            try {
                JSONObject categoryJson = jsonCategories.getJSONObject(i);
                category = new Category(categoryJson);
                categories.add(category);
            } catch (JSONException e) {}
        }
        return  categories;
    }

    private void getCategories() {
        new HttpAsyncTask("http://djemam.com/epsi/categories.json", new HttpAsyncTask.HttpAsyncTaskListener() {
            @Override
            public void webServiceDone(String categories) {
                try {
                    System.out.println("Nompe" + categories);
                    jsonCategories = new JSONArray(categories);
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
