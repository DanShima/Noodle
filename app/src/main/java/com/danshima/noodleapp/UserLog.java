package com.danshima.noodleapp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class holds info for the "my list" option in which the user can write down some review, experience
 * or a noodle dish he just tried.
 * Created by Giddy on 16/11/2017.
 */

public class UserLog {
    private String experience;
    private Date date;

    //to initialize a simpler, user version of noodle
    protected UserLog(String experience, Date date){
        this.experience = experience;
        this.date = date;
    }

    protected UserLog(String experience) {
        this(experience, new Date(java.lang.System.currentTimeMillis()));
    }
    public Date getDate(){
        return date;
    }


    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) { this.experience = experience; }

    /**
     * @return the string representation of a user experience log
     *
     */
    public String toString() {
        SimpleDateFormat newDate = new SimpleDateFormat("dd/MM/yy");
        String dateString = newDate.format(date);
        return "(" + dateString + ") " + experience;
    }
}
