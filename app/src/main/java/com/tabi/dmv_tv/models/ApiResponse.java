package com.tabi.dmv_tv.models;

import java.util.ArrayList;
import java.util.Date;

public class ApiResponse {
    private String language;
    private ArrayList<Playlist> playlists;
    private ArrayList<Category> categories;
    private ArrayList<TvSpecial> tvSpecials;
    private String lastUpdated;
    private String providerName;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<TvSpecial> getTvSpecials() {
        return tvSpecials;
    }

    public void setTvSpecials(ArrayList<TvSpecial> tvSpecials) {
        this.tvSpecials = tvSpecials;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
