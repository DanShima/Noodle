package com.danshima.noodleapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

/**
 * A SQLite helper that creates and gives access to the database
 * Created by Giddy on 04/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    //the name for the database
    private static final String DATABASENAME = "NoodleDB";
    //the initial version of the database
    private static final int DATABASEVERSION = 1;
    private Noodle noodle;

    DatabaseHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDatabase(db, 0, DATABASEVERSION);

    }

    /**
     * this method updates the database if the db helper's version number is higher than the version number on the db.
     *
     * @param db             The SQLite database
     * @param currentVersion The user's version number of the database
     * @param updatedVersion The new version of the database written in the helper's code
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int currentVersion, int updatedVersion) {
        updateDatabase(db, currentVersion, updatedVersion);
    }

    /**
     * This method is called when you want to set the database back to its previous version
     *
     * @param db             The SQLite database
     * @param currentVersion The user's version number of the database
     * @param updatedVersion The new version of the database written in the helper's code
     */
    public void onDowngrade(SQLiteDatabase db, int currentVersion, int updatedVersion) {

    }

    /**
     * This method is for adding new noodle dish to the database
     */
    private static void addNoodle(SQLiteDatabase database, Noodle noodle) {
        ContentValues noodleValues = new ContentValues();
        noodleValues.put("NAME", noodle.getName());
        noodleValues.put("DESCRIPTION", noodle.getDescription());
        noodleValues.put("IMAGEID", noodle.getPhotoID());
        noodleValues.put("RESTAURANT", noodle.getSuggestedRestaurant());
        database.insert("NOODLE", null, noodleValues);
        database.close();

    }

    private void updateDatabase(SQLiteDatabase db, int currentVersion, int updatedVersion) {
        if(currentVersion >= 1 || currentVersion < 1 ) {
            //execute SQL on the db and create a new NOODLE table
            db.execSQL("CREATE TABLE NOODLE ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGEID INTEGER, "
                    + "RESTAURANT TEXT, "
                    + "FAVORITE NUMERIC, "
                    + "CATEGORY TEXT);");
            addNoodle(db, new Noodle("Spicy Ramen", "Chicken broth, marinated pork, chilli and bean sprouts", R.drawable.spicyramen, "Totemo", "japanese"));
            addNoodle(db, new Noodle("Tokyo Ramen", "Dashi-based broth, marinated pork and fermented bamboo shoots", R.drawable.tokyo, "Totemo", "japanese"));
            addNoodle(db, new Noodle("Vegetarian Ramen", "Mushroom dashi-based broth, tofu, pak choi, miso and corn", R.drawable.vegetarianramen, "Ramen Manga", "japanese"));
            addNoodle(db, new Noodle("Miso Ramen", "Miso broth, marinated pork, egg, spring onion and bean sprouts", R.drawable.miso, "Cafe Steigman", "japanese"));
            addNoodle(db, new Noodle("Tonkatsu Ramen", "Pork bone based broth, grilled pork, spicy garlic with miso", R.drawable.tonkatsu, "Blue Light Yokohama", "japanese"));
            addNoodle(db, new Noodle("Shio Ramen", "Seasalt broth, pork, egg, quail and vegetable", R.drawable.shio, "Ai Ramen", "japanese"));
            addNoodle(db, new Noodle("Nabeyaki Udon", "Udon noodles in fish broth with chicken, shrimp, egg and leek", R.drawable.udon, "Ki-mama Ramen", "japanese"));
        }
        if(currentVersion <=2) {

        }
    }



}
