package com.danshima.noodleapp


import android.app.SearchManager
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.widget.AdapterView
import android.widget.CursorAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_searchable.*
import kotlinx.android.synthetic.main.toolbar_main.*


class SearchableActivity : MenuActivity() {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var database: SQLiteDatabase
    private lateinit var cursor: Cursor
    private lateinit var resultList: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)
        setSupportActionBar(toolbar)

        resultList = findViewById(R.id.search_result_list)
        handleIntent(intent)
    }

    /**
     * This method delivers the intent when the activity is invoked again.
     * when the user executes a search from this activity, we don't create
     * a new instance of this activity, because of launchMode="singleTop".=
     */
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    /**
     * This method decides what to do with the search request
     * @param intent The search request made by the user
     */
    private fun handleIntent(intent: Intent) {
        var intent = intent
        //if the activity was called to process a search request, do something with the search word/query
        try {
            intent = getIntent()
            if (Intent.ACTION_SEARCH == intent.action) {
                //handles the search
                val searchWord = intent.getStringExtra(SearchManager.QUERY)
                showSearchResult(searchWord)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "error there!", Toast.LENGTH_SHORT).show()
        }

    }

    /**
     * This method searches in the database to match the input given by the user.
     * @param input The search word or description input from the user
     * @return Cursor the cursor for finding the relevant information
     */

    private fun searchInDatabase(input: String): Cursor? {
        databaseHelper = DatabaseHelper(this)
        database = databaseHelper.readableDatabase
        val columns = arrayOf("_id", "NAME", "DESCRIPTION")
        val selectionString = "NAME LIKE '%$input%' OR DESCRIPTION LIKE '%$input%'"
        //sort the filtered list in alphabetical order
        val orderBy = "NAME" + " ASC"
        cursor = database.query("NOODLE", columns, selectionString, null, null, null, orderBy)
        cursor.moveToFirst()
        return cursor
    }


    /**
     * This method displays results for the given query in the designated list view.
     * @param query The search query
     */
    private fun showSearchResult(query: String) {
        searchInDatabase(query)
        if (cursor == null) {
            val toast = Toast.makeText(this, "No result found", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            //create a new cursor adapter
            val adapter = SimpleCursorAdapter(this@SearchableActivity,
                android.R.layout.simple_list_item_1, cursor, arrayOf("NAME"), intArrayOf(android.R.id.text1), 0)
            //set the cursor adapter to the list view
            search_result_list.adapter = adapter

            // Define the on-click listener for the list items
            search_result_list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                // Build the Intent used to open WordActivity with a specific word Uri
                val newIntent = Intent(this@SearchableActivity, DetailActivity::class.java)
                newIntent.putExtra(DetailActivity.CHOSEN_NOODLE_ITEM, id.toInt())
                startActivity(newIntent)
            }
        }
    }
}










