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

    //the index column name for use in where clauses;
    public static final String KEY_ID_NOODLE = "_id";
    //the names for some columns in the noodle database
    public static final String NOODLE_NAME_COLUMN = "NAME";
    public static final String NOODLE_DESCRIPTION_COLUMN = "DESCRIPTION";
    public static final String NOODLE_RESTAURANT_COLUMN = "RESTAURANT";

    //SQL statement to create a new database.
    public static final String DATABASE_CREATE_NOODLE = "create table " + DATABASE_TABLE_ONE + " ("
            + KEY_ID_NOODLE + " integer primary key autoincrement, "
            + NOODLE_NAME_COLUMN + " text not null, "
            + NOODLE_DESCRIPTION_COLUMN + " text not null, "
            + "IMAGEID INTEGER, "
            + NOODLE_RESTAURANT_COLUMN + " text not null, "
            + "CATEGORY INTEGER);";
    private Noodle noodle;


    public DatabaseHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    /**
     * This method is called when the database needs to be accessed for the first time
     * @param db The SQLite database in use
     */
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
     * This method is for adding new noodle dish to the SQLite database
     * @param database The SQLite database called "noodle"
     * @param noodle a Noodle object     *
     */
    private static void addNoodle(SQLiteDatabase database, Noodle noodle) {
        ContentValues noodleValues = new ContentValues();
        noodleValues.put(NOODLE_NAME_COLUMN, noodle.getName());
        noodleValues.put(NOODLE_DESCRIPTION_COLUMN, noodle.getDescription());
        noodleValues.put("IMAGEID", noodle.getPhotoID());
        noodleValues.put(NOODLE_RESTAURANT_COLUMN, noodle.getSuggestedRestaurant());
        noodleValues.put("CATEGORY", noodle.getCategoryNumber());

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
    }


    /**
     * This method creates the noodle database including data resources
     * @param db the SQLite database
     * @param oldVersion the user's version of the database
     * @param newVersion the new version written in the DatabaseHelper's code
     */
    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= 1) {
            //execute SQL on the db and create a new NOODLE table
            db.execSQL(DATABASE_CREATE_NOODLE);
            populateNoodleDatabase(db);
            if (oldVersion <= 2) {
            }
        }
    }

    /**
     * This method populates the database with noodle dishes.
     * @param db The Noodle database
     */
    //category numbers are 1)Japanese 2)Vietnamese 3)Thai 4)Korean 5)Chinese
    private void populateNoodleDatabase(SQLiteDatabase db){
        addNoodle(db, new Noodle("Spicy Ramen", "Chicken broth, marinated pork, chilli and bean sprouts", R.drawable.spicyramen, "Totemo Ramen\n Sankt Eriksgatan 70, 11320 Stockholm", 1));
        addNoodle(db, new Noodle("Tokyo Ramen", "Dashi-based broth, marinated pork and fermented bamboo shoots", R.drawable.tokyo, "Cafe Stiernan\nÖsterlånggaten 45, 11131 Stockholm", 1));
        addNoodle(db, new Noodle("Vegetarian Ramen", "Mushroom dashi-based broth, tofu, pak choi, miso and corn", R.drawable.vegetarianramen, "Ai Ramen\nErstagatan 22, 11636 Stockholm", 1));
        addNoodle(db, new Noodle("Miso Ramen", "Miso broth, marinated pork, egg, spring onion and bean sprouts", R.drawable.miso, "Cafe Stiernan\nÖsterlånggaten 45, 11131 Stockholm", 1));
        addNoodle(db, new Noodle("Tonkatsu Ramen", "Pork bone based broth, grilled pork, spicy garlic with miso", R.drawable.tonkatsu, "Blue Light Yokohama\nÅsögatan 170, 11632 Stockholm", 1));
        addNoodle(db, new Noodle("Shio Ramen", "Seasalt broth, pork, egg, quail and vegetable", R.drawable.shio, "Ramen Manga\nPilgatan 28, 11223 Stockholm", 1));
        addNoodle(db, new Noodle("Nabeyaki Udon", "Udon noodles in fish broth with chicken, shrimp, egg and leek", R.drawable.udon, "Ki-mama Ramen\nBirger Jarlsgatan 93, 11356 Stockholm", 1));
        addNoodle(db, new Noodle("Cao Lau", "Braised five spice porkbelly, soy sauce, garlic, shallots, fish mint, crown daisy leaves, cilantro, Thai basil, Chinese chives, gem salad, mung bean sprouts, deep fried noodle dough croutons, broth, chili paste with lemon grass.",
                R.drawable.caolau, "Minh Mat\nOdengatan 94, 113 33 Stockholm", 2));
        addNoodle(db, new Noodle("Bun Bo Hue", "Rich, spicy vermicelli noodle soup with deep layers of flavors. Served with, banana flowers,  tender slices of beef and pork, lemongrass, and lots of fresh herbs.",
                R.drawable.bonbohue, "Sankt Eriksgatan 124, 113 31 Stockholm", 2));
        addNoodle(db, new Noodle("Beef Pho", "A comforting richly seasoned beef broth is ladled over rice noodles and thinly sliced beef.", R.drawable.beefpho, "EatNam\nOdengatan 85, 113 22 Stockholm", 2));
        addNoodle(db, new Noodle("Hoanh Thanh Chay", "Vegetarian wonton soup with black bean dumplings, tofu balls with five spices, shiitake, shimeij, pak choy, lemon grass bouillon.", R.drawable.hoanh_thanh_chay,
                "Minh Mat\nOdengatan 94, 113 33 Stockholm", 2));
        addNoodle(db, new Noodle("Chicken Pho", "A light chicken broth with banh pho noodles and tender poached chicken.", R.drawable.chickenpho, "Pho & Bun\nTegnérgatan 19, 111 40 Stockholm", 2));
        addNoodle(db, new Noodle("Pad See Ew", "A popular and delicious Thai noodle dish made with flat, wide rice noodles fried in soy sauce with meat, egg, and vegetables.",
                R.drawable.pad_see_ew,"Waan Thai\nSankt Eriksgatan 92, 113 62 Stockholm", 3));
        addNoodle(db, new Noodle("Pad Thai", "Stir fry rice noodles with shrimp, tofu, eggs and bean sprouts with sweet sour sauce", R.drawable.padthai, "Tjabba Thai\nWallingatan 7, 111 60 Stockholm", 3));
        addNoodle(db, new Noodle("Tom Yum Ramen", "Sour and spicy soup made from lemongrass, kaffir lime leaves & galangal filled with prawns, mushrooms, tomatoes , onions and rice vermicelli noodles.",
                R.drawable.thaispicy, "Ramen Ki-mama\nBirger Jarlsgatan 93, 113 56 Stockholm", 3));
        addNoodle(db, new Noodle("Jajang Myeon", " Korean Chinese noodle dish topped with a thick sauce made of chunjang, diced pork and vegetables.",
                R.drawable.jajangmyeon, "Koreana\nLuntmakargatan 76, 113 51 Stockholm", 4));
        addNoodle(db, new Noodle("Japchae", "Stir-fried glass noodles and vegetables is a sweet and savory dish popular in Korean cuisine, with sweet potatoe noodles, assorted vegetables, meat, and mushrooms",
                R.drawable.japchae, "Arirang\nLuntmakargatan 65, 113 58 Stockholm", 4));
        addNoodle(db, new Noodle("Kimchi Ramen", "Noodles in soy broth, yakiniku(beef), fermented cabbage called kimchi, bean sprouts and sesame", R.drawable.kimchiramen,
                "Ki-mama Ramen\nBirger Jarlsgatan 93, 11356 Stockholm", 4));
        addNoodle(db, new Noodle("Mul Naengmyeon", "Nourishing, icy-cold broth with chewy buckwheat noodles that is very popular in the summer.",
                R.drawable.colramen, "Kimchi Restaurant\nLuntmakargatan 95, 113 51 Stockholm", 4));
        addNoodle(db, new Noodle("Wonton Soup", "Noodles in soy broth, leek, shrimp and pork dumplings.", R.drawable.wonton, "Ki-mama Ramen\nBirger Jarlsgatan 93, 11356 Stockholm", 5));
        addNoodle(db, new Noodle("Tofu hotpot", "Vegetarian noodle soup with tomato-based broth, tofu, shitake, and zucchini", R.drawable.tofu, "Lao Wai\nLuntmakargatan 74, 113 51 Stockholm", 5));
        addNoodle(db, new Noodle("Beef Noodle Soup", "Chinese Noodle soup with tender beef and pakchoi", R.drawable.chinesebeef, "Mandarin City\nSveavägen 33, 111 34 Stockholm", 5));
        addNoodle(db, new Noodle("Dandan Mian", "A spicy sauce containing preserved vegetables, chili oil, Sichuan pepper, minced pork, and scallions served over noodles",
                R.drawable.spicydandan, "Waipo Stockholm\nDrottninggatan 25, 111 51 Stockholm", 5));
    }

}
