package com.danshima.noodleapp

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.danshima.noodleapp.data.Noodle


/**
 * A SQLite helper that creates and gives access to the database
 * Created by Giddy on 04/11/2017.
 */

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASENAME, null, DATABASEVERSION) {

    private lateinit var database: SQLiteDatabase
    private lateinit var cursor: Cursor

    /**
     * This method fetches names of the noodles from the database
     * @return cursor that finds the specific info inside the database
     */
    val name: Cursor
        get() {
            cursor = database.query("NOODLE", arrayOf("_id", "NAME"), null, null, null, null, null)
            return cursor
        }

    /**
     * This method is called when the database needs to be accessed for the first time
     * @param db The SQLite database in use
     */
    override fun onCreate(db: SQLiteDatabase) {
        updateDatabase(db, 0, DATABASEVERSION)

    }

    /**
     * this method updates the database if the db helper's version number is higher than the version number on the db.
     *
     * @param db             The SQLite database
     * @param currentVersion The user's version number of the database
     * @param updatedVersion The new version of the database written in the helper's code
     */
    override fun onUpgrade(db: SQLiteDatabase, currentVersion: Int, updatedVersion: Int) {
        //drop the old table and create a new one
        db.execSQL("DROP TABLE IF EXISTS $DATABASE_TABLE_ONE")
        updateDatabase(db, currentVersion, updatedVersion)
    }

    /**
     * This method creates the noodle database including data resources
     * @param db the SQLite database
     * @param oldVersion the user's version of the database
     * @param newVersion the new version written in the DatabaseHelper's code
     */
    private fun updateDatabase(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 1) {
            //execute SQL on the db and create a new NOODLE table
            db.execSQL(DATABASE_CREATE_NOODLE)
            //populateNoodleDatabase(db)
            db.execSQL("ALTER TABLE $DATABASE_TABLE_ONE ADD COLUMN FAVORITE NUMERIC;")
        }
    }

    companion object {

        //the name for the database
        private val DATABASENAME = "NoodleDB"
        //the initial version of the database
        private val DATABASEVERSION = 1
        private val DATABASE_TABLE_ONE = "NOODLE"

        //the index column name for use in where clauses;
        private val KEY_ID_NOODLE = "_id"
        //the names for some columns in the noodle database
        private val NOODLE_NAME_COLUMN = "NAME"
        private val NOODLE_DESCRIPTION_COLUMN = "DESCRIPTION"
        private val NOODLE_RESTAURANT_COLUMN = "RESTAURANT"

        //SQL statement to create a new Noodle database.
        private val DATABASE_CREATE_NOODLE = ("create table " + DATABASE_TABLE_ONE + " ("
            + KEY_ID_NOODLE + " integer primary key autoincrement, "
            + NOODLE_NAME_COLUMN + " text not null, "
            + NOODLE_DESCRIPTION_COLUMN + " text not null, "
            + "IMAGEID INTEGER, "
            + NOODLE_RESTAURANT_COLUMN + " text not null, "
            + "CATEGORY INTEGER);")
    }

}
