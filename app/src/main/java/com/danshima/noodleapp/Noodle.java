package com.danshima.noodleapp;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The noodle class defines a single noodle dish with its attributes
 * Created by Giddy on 04/11/2017.
 */

public class Noodle {
    private String name;
    private String description;
    private int photoID;
    private String suggestedRestaurant;
    private int categoryNumber;
    private Date visitDate;



    protected Noodle(String name, String description, int photo, String suggestedRestaurant, int categoryNumber) {
        this.name = name;
        this.description = description;
        this.photoID = photo;
        this.suggestedRestaurant = suggestedRestaurant;
        this.categoryNumber = categoryNumber;

    }

    //to initialize a simpler, user version of noodle
    protected Noodle(String name, Date date){
        this.name = name;
        this.visitDate = date;
    }

    //to initialize a version of noodle for the single-line log entries
    protected Noodle(String name) {
        this(name, new Date(java.lang.System.currentTimeMillis()));
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

    public Date getDate(){
        return visitDate;
    }

    /**
     * @return the string representation of a noodle dish (the user log version)
     */
    public String toString() {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yy");
        String dateString = date.format(visitDate);
        return "(" + dateString + ") " + name;
    }


}

