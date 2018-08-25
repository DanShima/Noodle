package com.danshima.noodleapp

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast

class NoodleActivity : MenuActivity() {
    private val cursor: Cursor? = null
    private var database: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noodle)
        //set up toolbar as the normal app bar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //fetch data from the database
        val databaseHelper = DatabaseHelper(this)
        val noodleListPerCategory = findViewById<ListView>(R.id.selected_noodleList)

        try {
            database = databaseHelper.readableDatabase
            databaseHelper.name

            //create the cursor adapter to fill the list view with values from the database
            val listAdapter = SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, arrayOf("NAME"),
                intArrayOf(android.R.id.text1), 0)
            noodleListPerCategory.adapter = listAdapter
        } catch (e: SQLiteException) {
            e.printStackTrace()
            val toast = Toast.makeText(this, "Database is not working!", Toast.LENGTH_SHORT)
            toast.show()
        }

        //show item detail using the listener when an item is clicked
        val itemClickListener = AdapterView.OnItemClickListener { noodleListPerCategory, view, position, id ->
            //starts DetailActivity
            val intent = Intent(this@NoodleActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.CHOSEN_NOODLE_ITEM, id.toInt())
            startActivity(intent)
        }
        //connects the listener to the list view
        noodleListPerCategory.onItemClickListener = itemClickListener
    }

    /**
     * This method is called when the database and cursor need to be closed.
     * They are closed when the cursor adapter doesn't need them anymore.
     */
    public override fun onDestroy() {
        super.onDestroy()
        cursor!!.close()
        database!!.close()
    }

}

