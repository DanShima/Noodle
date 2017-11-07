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
    private static final String DATABASENAME = "NoodleDB";
    //the initial version of the database
    private static final int DATABASEVERSION = 1;
    private static final String DATABASE_TABLE_ONE = "NOODLE";
    private static final String DATABASE_TABLE_TWO = "CATEGORY";
    //SQL statement to create a new database.
    private static final String DATABASE_CREATE_NOODLE = "create table DATABASE_TABLE_ONE ("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NAME TEXT, "
            + "DESCRIPTION TEXT, "
            + "IMAGEID INTEGER, "
            + "RESTAURANT TEXT, "
            + "CATEGORY INTEGER);";
    private static final String DATABASE_CREATE_CATEGORY = "create table DATABASE_TABLE_TWO ("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NAME TEXT);";

    private Noodle noodle;

    //the index column name for use in where clauses;
    public static final String KEY_ID_NOODLE = "_id";
    public static final String NOODLE_NAME_COLUMN = "NAME";
    public static final String NOODLE_DESCRIPTION_COLUMN = "DESCRIPTION";
    public static final String NOODLE_RESTAURANT_COLUMN = "RESTAURANT";
    public static final String NOODLE_CATEGORY_COLUMN = "CATEGORY_NOODLE";

    public static final String CATEGORY_ID = "_id_Category";
    public static final String CATEGORY_NAME = "NAME_CATEGORY";


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }
    public DatabaseHelper(Context context) {
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
    private static void addNoodle(SQLiteDatabase database, String name, String description, int imageid, String restaurant, int category) {
        ContentValues noodleValues = new ContentValues();
        noodleValues.put("NAME", name);
        noodleValues.put("DESCRIPTION", description);
        noodleValues.put("IMAGEID", imageid);
        noodleValues.put("RESTAURANT", restaurant);
        noodleValues.put("CATEGORY", category);

        database.insert(DATABASE_TABLE_ONE, null, noodleValues);
        //database.close();

    }

    private void addNoodleTest(SQLiteDatabase db){
        ContentValues noodleValues = new ContentValues();
        noodleValues.put("NAME", "Spicy Ramen");
        noodleValues.put("DESCRIPTION", "Chicken broth, marinated pork, chilli and bean sprouts");
        noodleValues.put("IMAGEID", R.drawable.spicyramen);
        noodleValues.put("RESTAURANT", "TOTEMO");
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

    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= 1 || oldVersion < 1 ) {
            //execute SQL on the db and create a new NOODLE table
            db.execSQL(DATABASE_CREATE_NOODLE);

            addNoodleTest(db);
            addNoodle(db,"Spicy Ramen", "Chicken broth, marinated pork, chilli and bean sprouts", R.drawable.spicyramen, "Totemo", 1);
            addNoodle(db,"Tokyo Ramen", "Dashi-based broth, marinated pork and fermented bamboo shoots", R.drawable.tokyo, "Totemo", 1);
            addNoodle(db,"Vegetarian Ramen", "Mushroom dashi-based broth, tofu, pak choi, miso and corn", R.drawable.vegetarianramen, "Ramen Manga", 1);
            addNoodle(db,"Miso Ramen", "Miso broth, marinated pork, egg, spring onion and bean sprouts", R.drawable.miso, "Cafe Steigman", 1);
            addNoodle(db,"Tonkatsu Ramen", "Pork bone based broth, grilled pork, spicy garlic with miso", R.drawable.tonkatsu, "Blue Light Yokohama", 1);
            addNoodle(db,"Shio Ramen", "Seasalt broth, pork, egg, quail and vegetable", R.drawable.shio, "Ai Ramen", 1);
            addNoodle(db,"Nabeyaki Udon", "Udon noodles in fish broth with chicken, shrimp, egg and leek", R.drawable.udon, "Ki-mama Ramen", 1);
            db.execSQL(DATABASE_CREATE_CATEGORY);
            addCategoryTest(db, "Japanese");
            addCategoryTest(db, "Vietnamese");
            addCategoryTest(db, "Korean");
            addCategoryTest(db, "Chinese");
            addCategoryTest(db, "Thai");


        db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
            if (oldVersion <= 2) {

            }
            }
    }


}
