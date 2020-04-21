package com.example.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Category {

    private int id;
    private String title;
    private String products_url;

    public Category(int id, String title, String products_url) {
        this.id = id;
        this.title = title;
        this.products_url = products_url;
    }

    public Category(JSONObject categoryJSON) throws JSONException {
        this.id = categoryJSON.getInt("category_id");
        this.title = categoryJSON.getString("title");
        this.products_url = categoryJSON.getString("products_url");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProducts_url() {
        return products_url;
    }

    public void setProducts_url(String products_url) {
        this.products_url = products_url;
    }
}
