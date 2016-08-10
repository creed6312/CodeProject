package com.example.shanesardinha.codeproject.Models;

/**
 * Created by shanesardinha on 2016/08/10.
 */
public class Artist {
    private String name ;
    private Image[] image;
    private int ontour;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image[] getImage() {
        return image;
    }

    public void setImage(Image[] image) {
        this.image = image;
    }

    public String isOnTour() {
        if (ontour == 1)
            return "Yes";
        else return "No";
    }

    public void setOnTour(int onTour) {
        this.ontour = onTour;
    }
}
