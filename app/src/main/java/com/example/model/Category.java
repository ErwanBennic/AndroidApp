package com.example.model;

public class Category {

    private String title;
    private String products_url;

    public Category(String title, String products_url) {
        this.title = title;
        this.products_url = products_url;
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
