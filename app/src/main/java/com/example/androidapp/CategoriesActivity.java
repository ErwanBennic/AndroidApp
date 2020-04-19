package com.example.androidapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.model.Category;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {

    private CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        setTitle("Catégories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final List<Category> categories = new ArrayList<>();
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

}
