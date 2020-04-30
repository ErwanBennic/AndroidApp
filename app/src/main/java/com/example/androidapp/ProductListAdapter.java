package com.example.androidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.model.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends ArrayAdapter<Products> {

    public ProductListAdapter(@NonNull Context context, int resource, @NonNull List<Products> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.products_cell, null);
        TextView title, description, pictureurl;
        Products products = getItem(position);
        title = convertView.findViewById(R.id.product_title);
        title.setText(products.getName());
        return convertView;

    }
}
