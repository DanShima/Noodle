package com.danshima.noodleapp;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Giddy on 17/11/2017.
 */

/*public class LogList {
    private ArrayList<UserLog> experiences;
    public static final String SHARED_PREFS_NAME = "MY_SHARED_PREF";

    public LogList() {
        experiences = new ArrayList<>();
    }

    /**
     * This method adds a new experience or log (user input) to the ArrayList
     * @param log The entry written by the user
     */
   /* public void addLog(String log) {
        //convert the UserLog object into string for the arrayList
        //log = new UserLog(newExp);
        UserLog newLog = new UserLog(log);
        newLog.toString();
        experiences.add(0, newLog);
    }

    /**
     * This method removes an entry from the log list if the user long clicks it.
     *  @param position the index of the entry
     *  */
   /* public void removeLog(int position) {
        UserLog log = experiences.get(position);
        experiences.remove(log);

    }

    public boolean saveArray() {
        //enter key and mode(the file can only be accessed using calling app)
        SharedPreferences sp = this.getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Set<String> set = new HashSet<>();
        set.addAll(experiences);
        editor.putStringSet("list", set);
        return editor.commit();
    }

    /**
     * This method retrieves the data stored through SharedPreferences.
     * @return the stored ArrayList
     */

  /*  public ArrayList<String> getArray() {
        SharedPreferences sp = this.getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE);
        //if shared preference is null, the method return empty Hashset and not null
        Set<String> set = sp.getStringSet("list", new HashSet<String>());
        return new ArrayList<>(set);
    }




}*/
