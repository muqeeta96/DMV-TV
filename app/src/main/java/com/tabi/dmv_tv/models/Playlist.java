package com.tabi.dmv_tv.models;

import java.util.ArrayList;

public class Playlist {
    private String name;
    private ArrayList<String> itemIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(ArrayList<String> itemIds) {
        this.itemIds = itemIds;
    }
}
