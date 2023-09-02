package com.tabi.dmv_tv.models;

import java.util.ArrayList;
import java.util.Date;

public class Content {
    private ArrayList<Video> videos;
    private Integer duration;
    private String dateAdded;

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
