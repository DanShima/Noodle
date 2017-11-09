package com.danshima.noodleapp;



/**
 * Created by Giddy on 04/11/2017.
 */

public class Noodle {
    private String name;
    private String description;
    private int photoID;
    private String suggestedRestaurant;
    private int categoryNumber;



    protected Noodle(String name, String description, int photo, String suggestedRestaurant, int categoryNumber) {
        this.name = name;
        this.description = description;
        this.photoID = photo;
        this.suggestedRestaurant = suggestedRestaurant;
        this.categoryNumber = categoryNumber;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public String getSuggestedRestaurant() {
        return suggestedRestaurant;
    }

    public void setSuggestedRestaurant(String suggestedRestaurant) {
        this.suggestedRestaurant = suggestedRestaurant;
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    /**
     * @return the string representation of a noodle dish with its name
     */
    public String toString() {
        return this.name;
    }
}

