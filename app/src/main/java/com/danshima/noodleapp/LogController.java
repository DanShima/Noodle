package com.danshima.noodleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.lang.UScript;

/**
 * Created by Giddy on 17/11/2017.
 */

public class LogController extends SQLiteOpenHelper {
    private UserLog log;
    //the name for the database
    private static final String LOGDATABASE = "LogDB";
    //the initial version of the database
    private static final int DBVERSION = 1;
    private static final String DATABASE_TABLE_LOG = "EXPERIENCELOG";

    //the index column name for use in where clauses;
    public static final String KEY_ID_LOG = "_id";
    //the names for some columns in the noodle database
    public static final String LOG_COLUMN = "LOG";

    private static final String DATABASE_CREATE_LOG = "create table " + DATABASE_TABLE_LOG + " ("
            + KEY_ID_LOG + " integer primary key autoincrement, "
            + LOG_COLUMN + " text not null);";

    public LogController(Context context) {
        super(context, LOGDATABASE, null, DBVERSION);
    }

    /**
     * This method is called when the database needs to be accessed for the first time
     * @param db The SQLite database in use
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDatabase(db, 0, DBVERSION);
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
        //drop the old table and create a new one
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_LOG);
        updateDatabase(db, currentVersion, updatedVersion);
    }

    /**
     * This method is for adding new noodle dish to the SQLite database
     *
     */
    public boolean addLog(String input) {
       //serLog log = new UserLog(input);
       //tring ninput = log.toString();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues logValues = new ContentValues();
        logValues.put(LOG_COLUMN, input);
        logValues.put(LOG_COLUMN, "Hello");
        try {
            db.insert(DATABASE_TABLE_LOG, null, logValues);
        } catch(SQLiteException e){
            e.printStackTrace();
        }
       // db.close();
        return true;
    }

    /**
     * This method removes an entry from the log list if the user long clicks it.
     *  @param position the index of the entry
     *  */
    public Integer removeLog(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DATABASE_TABLE_LOG, KEY_ID_LOG + " =?", new String[] {Integer.toString(position)});

    }

    /**
     * This method creates the noodle database including data resources
     * @param db the SQLite database
     * @param oldVersion the user's version of the database
     * @param newVersion the new version written in the DatabaseHelper's code
     */
    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            //execute SQL on the db and create a new NOODLE table
            db.execSQL(DATABASE_CREATE_LOG);
        }
    }








}
