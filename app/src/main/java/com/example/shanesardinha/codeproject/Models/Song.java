package com.example.shanesardinha.codeproject.Models;

/**
 * Created by shanesardinha on 2016/08/08.
 */
public class Song {

    private String name;
    private Artist artist;
    private int playcount;
    private int listeners;
    private Image[] image;

    public int getPlayCount() {
        return playcount;
    }

    public void setPlayCount(int playCount) {
        this.playcount = playCount;
    }

    public int getListeners() {
        return listeners;
    }

    public void setListeners(int listeners) {
        this.listeners = listeners;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Image[] getImage() {
        return image;
    }

    public void setImage(Image[] image) {
        this.image = image;
    }
}
