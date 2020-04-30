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
    private List<Category> categories;

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
        this.getCategories();
    }

    private void generateCategoriesListView() {
        ListView listView = findViewById(R.id.categories_listview);
        categoriesAdapter = new CategoriesAdapter(this,R.layout.categories_cell, categories);
        listView.setAdapter(categoriesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductListActivity.display(CategoriesActivity.this, categories.get(position).getProducts_url());
            }
        });
    }

    private List<Category> initCategories(String result) throws JSONException {

        JSONArray jsonCategories = new JSONArray(result);
        List<Category> categories = new ArrayList<>();

        int nbCategories = jsonCategories.length();
        for(int i = 0; i < nbCategories; i++) {
            Category category = null;
            try {
                JSONObject categoryJson = jsonCategories.getJSONObject(i);
                category = new Category(categoryJson);
                categories.add(category);
            } catch (JSONException e) {

            }
        }
        return categories;
    }

    private void getCategories() {
        new HttpAsyncTask("http://djemam.com/epsi/categories.json", new HttpAsyncTask.HttpAsyncTaskListener() {
            @Override
            public void webServiceDone(String result) {
                try {
                    categories = initCategories(result);
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

}
