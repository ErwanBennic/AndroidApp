package com.example.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Products {

    private String name;
    private String description;
    private String pictures_url;

    public Products(JSONObject productJson) throws JSONException {
        this.name = productJson.getString("name");
        this.description = productJson.getString("description");
        this.pictures_url = productJson.getString("pictures_url");
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

    public String getPictures_url() {
        return pictures_url;
    }

    public void setPictures_url(String pictures_url) {
        this.pictures_url = pictures_url;
    }
}
