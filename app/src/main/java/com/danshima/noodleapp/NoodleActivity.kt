package com.danshima.noodleapp

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_noodle.*
import kotlinx.android.synthetic.main.toolbar_main.*

class NoodleActivity : MenuActivity() {
    private lateinit var cursor: Cursor
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noodle)

        setSupportActionBar(toolbar)

        //fetch data from the database
        val databaseHelper = DatabaseHelper(this)

        try {
            database = databaseHelper.readableDatabase
            databaseHelper.name

            //create the cursor adapter to fill the list view with values from the database
            val listAdapter = SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, arrayOf("NAME"),
                intArrayOf(android.R.id.text1), 0)
            selected_noodleList.adapter = listAdapter
        } catch (e: SQLiteException) {
            e.printStackTrace()
            Toast.makeText(this, "Database is not working!", Toast.LENGTH_SHORT).show()
        }

        //show item detail using the listener when an item is clicked
        val itemClickListener = AdapterView.OnItemClickListener { noodleListPerCategory, view, position, id ->
            //starts DetailActivity
            val intent = Intent(this@NoodleActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.CHOSEN_NOODLE_ITEM, id.toInt())
            startActivity(intent)
        }
        //connects the listener to the list view
        selected_noodleList.onItemClickListener = itemClickListener
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

