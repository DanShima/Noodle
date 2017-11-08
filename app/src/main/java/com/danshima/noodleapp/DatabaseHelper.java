package com.danshima.noodleapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.provider.SyncStateContract;


/**
 * A SQLite helper that creates and gives access to the database
 * Created by Giddy on 04/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //the name for the database
    public static final String DATABASENAME = "NoodleDB";
    //the initial version of the database
    public static final int DATABASEVERSION = 1;
    public static final String DATABASE_TABLE_ONE = "NOODLE";
    public static final String DATABASE_TABLE_TWO = "CATEGORY";

    //the index column name for use in where clauses;
    public static final String KEY_ID_NOODLE = "_id";
    //the names for each column in the noodle database
    public static final String NOODLE_NAME_COLUMN = "NAME";
    public static final String NOODLE_DESCRIPTION_COLUMN = "DESCRIPTION";
    public static final String NOODLE_RESTAURANT_COLUMN = "RESTAURANT";

    //the names for each column in the category database
    public static final String CATEGORY_ID = "_id_Category";
    public static final String CATEGORY_NAME = "NAME_CATEGORY";

    //SQL statement to create a new database.
    public static final String DATABASE_CREATE_NOODLE = "create table " + DATABASE_TABLE_ONE + " ("
            + KEY_ID_NOODLE + " integer primary key autoincrement, "
            + NOODLE_NAME_COLUMN + " text not null, "
            + NOODLE_DESCRIPTION_COLUMN + " text not null, "
            + "IMAGEID INTEGER, "
            + NOODLE_RESTAURANT_COLUMN + " text not null, "
            + "CATEGORY INTEGER);";

    public static final String DATABASE_CREATE_CATEGORY = "create table " + DATABASE_TABLE_TWO + " ("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NAME TEXT);";

    private Noodle noodle;


    public DatabaseHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateCategoryDatabase(db, 0, DATABASEVERSION);
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
    private static void addNoodle(SQLiteDatabase database, String name, String description, int imageid, String restaurant, int category) {
        ContentValues noodleValues = new ContentValues();
        noodleValues.put(NOODLE_NAME_COLUMN, name);
        noodleValues.put(NOODLE_DESCRIPTION_COLUMN, description);
        noodleValues.put("IMAGEID", imageid);
        noodleValues.put(NOODLE_RESTAURANT_COLUMN, restaurant);
        noodleValues.put("CATEGORY", category);

        database.insert(DATABASE_TABLE_ONE, null, noodleValues);
        //database.close();

    }


    //just to test one hard coded noodle dish
    private void addNoodleTest(SQLiteDatabase db) {
        ContentValues noodleValues = new ContentValues();
        noodleValues.put(NOODLE_NAME_COLUMN, "Spicy Ramen");
        noodleValues.put(NOODLE_DESCRIPTION_COLUMN, "Chicken broth, marinated pork, chilli and bean sprouts");
        noodleValues.put("IMAGEID", R.drawable.spicyramen);
        noodleValues.put(NOODLE_RESTAURANT_COLUMN, "TOTEMO");
        noodleValues.put("CATEGORY", 1);
        db.insert(DATABASE_TABLE_ONE, null, noodleValues);
        //db.close();
    }

    private void addCategoryTest(SQLiteDatabase db, String name) {
        ContentValues categoryValues = new ContentValues();
        categoryValues.put("NAME", name);
        db.insert(DATABASE_TABLE_TWO, null, categoryValues);
        //db.close();
    }

    private void updateCategoryDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DATABASE_CREATE_CATEGORY);
        addCategoryTest(db, "Japanese");
        addCategoryTest(db, "Vietnamese");
        addCategoryTest(db, "Korean");
        addCategoryTest(db, "Chinese");
        addCategoryTest(db, "Thai");
    }


    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= 1 || oldVersion < 1) {
            //execute SQL on the db and create a new NOODLE table
            db.execSQL(DATABASE_CREATE_NOODLE);
            populateNoodleDatabase(db);
            if (oldVersion <= 2) {

            }
        }
    }

    //category numbers are 1)Japanese 2)Vietnamese 3)Thai 4)Korean 5)Chinese
    private void populateNoodleDatabase(SQLiteDatabase db){
        addNoodle(db, "Spicy Ramen", "Chicken broth, marinated pork, chilli and bean sprouts", R.drawable.spicyramen, "Totemo Ramen\n Sankt Eriksgatan 70, 11320 Stockholm", 1);
        addNoodle(db, "Tokyo Ramen", "Dashi-based broth, marinated pork and fermented bamboo shoots", R.drawable.tokyo, "Cafe Stiernan\nÖsterlånggaten 45, 11131 Stockholm", 1);
        addNoodle(db, "Vegetarian Ramen", "Mushroom dashi-based broth, tofu, pak choi, miso and corn", R.drawable.vegetarianramen, "Ai Ramen\nErstagatan 22, 11636 Stockholm", 1);
        addNoodle(db, "Miso Ramen", "Miso broth, marinated pork, egg, spring onion and bean sprouts", R.drawable.miso, "Cafe Stiernan\nÖsterlånggaten 45, 11131 Stockholm", 1);
        addNoodle(db, "Tonkatsu Ramen", "Pork bone based broth, grilled pork, spicy garlic with miso", R.drawable.tonkatsu, "Blue Light Yokohama\nÅsögatan 170, 11632 Stockholm", 1);
        addNoodle(db, "Shio Ramen", "Seasalt broth, pork, egg, quail and vegetable", R.drawable.shio, "Ramen Manga\nPilgatan 28, 11223 Stockholm", 1);
        addNoodle(db, "Nabeyaki Udon", "Udon noodles in fish broth with chicken, shrimp, egg and leek", R.drawable.udon, "Ki-mama Ramen\nBirger Jarlsgatan 93, 11356 Stockholm", 1);
        addNoodle(db, "Cao Lau", "Braised five spice porkbelly, soy sauce, garlic, shallots, fish mint, crown daisy leaves, cilantro, Thai basil, Chinese chives, gem salad, mung bean sprouts, deep fried noodle dough croutons, broth, chili paste with lemon grass.",
                R.drawable.caolau, "Minh Mat\nOdengatan 94, 113 33 Stockholm", 2);
        addNoodle(db, "Bun Bo Hue", "Rich, spicy vermicelli noodle soup with deep layers of flavors. Served with, banana flowers,  tender slices of beef and pork, lemongrass, and lots of fresh herbs.",
                R.drawable.bonbohue, "Sankt Eriksgatan 124, 113 31 Stockholm", 2);
        addNoodle(db, "Jajang Myeon", "test", R.drawable.jajangmyeon, "Koreana\nLuntmakargatan 76, 113 51 Stockholm", 4);
        addNoodle(db, "Wonton Soup", "Noodles in soy broth, leek, shrimp and pork dumplings.", R.drawable.wonton, "Ki-mama Ramen\nBirger Jarlsgatan 93, 11356 Stockholm", 5);
        addNoodle(db, "Pad Thai", "Stir fry rice noodles with shrimp, tofu, eggs and bean sprouts with sweet sour sauce", R.drawable.padthai, "Tjabba Thai\nWallingatan 7, 111 60 Stockholm", 3);
    }






}
