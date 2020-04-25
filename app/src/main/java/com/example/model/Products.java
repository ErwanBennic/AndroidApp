package com.example.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Products {

    private String nom;
    private String description;
    private String pictures_url;

    public Products(JSONObject productJson) throws JSONException {
        this.nom = productJson.getString("nom");
        this.description = productJson.getString("description");
        this.pictures_url = productJson.getString("pictures_url");
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
