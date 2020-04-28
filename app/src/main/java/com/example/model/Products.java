package com.example.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Products {

    private String name;
    private String description;
    private String picture_url;

    public Products(JSONObject productJson) throws JSONException {
        this.name = productJson.getString("name");
        this.description = productJson.getString("description");
        this.picture_url = productJson.getString("picture_url");
    }

    public String getName() {
        return name;
    }
    public void setNom(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String pictures_url) {
        this.picture_url = pictures_url;
    }
}
