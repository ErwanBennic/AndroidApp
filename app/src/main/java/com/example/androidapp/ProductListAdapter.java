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

public class ProductListAdapter extends ArrayAdapter<Products> {

    Context context;
    Products product;
    ArrayList<Products> arrayList;

    public ProductListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Products> objects) {
        super(context, resource, objects);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Products getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.activity_product_list, null);

        TextView name, description, pictureurl;

        name =(TextView) convertView.findViewById(R.id.ProductName);
        description = (TextView) convertView.findViewById(R.id.ProductDescription);
        pictureurl = (TextView) convertView.findViewById(R.id.ProductPictureURL);

        name.setText(arrayList.get(position).getName());
        description.setText(arrayList.get(position).getDescription());
        pictureurl.setText(arrayList.get(position).getPictures_url());

        return convertView;


        /*LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.products_cell, null);

        //ListView produitslist = convertView.findViewById(R.id.ProduitsListView);
        TextView textViewName = convertView.findViewById(R.id.textViewProducts);
        //TextView textViewDescription = convertView.findViewById(R.id.textViewProductsDescrip);
        //TextView textViewPictureURL = convertView.findViewById(R.id.textViewPictureProduct);

        produitslist.getAdapter();
        ListView.getDefaultSize(300,10);
        ListView.generateViewId();

        product = getItem(position);
        textViewName.setText(product.getName());
        //textViewDescription.setText(product.getDescription());
        //textViewPictureURL.setText(product.getPictures_url());


        return convertView;*/
    }
}
