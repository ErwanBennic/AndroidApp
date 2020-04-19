package com.example.androidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.model.Category;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class CategoriesAdapter extends ArrayAdapter<Category> {

    public CategoriesAdapter(@NonNull Context context, int resource, @NonNull List<Category> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.categories_cell, null);

        TextView textViewName = convertView.findViewById(R.id.categorie_title);
        Category category = getItem(position);
        textViewName.setText(category.getTitle());
        return convertView;
    }
}