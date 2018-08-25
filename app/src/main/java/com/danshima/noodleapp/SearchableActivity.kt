package com.danshima.noodleapp


import android.app.SearchManager
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.AdapterView
import android.widget.CursorAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast


class SearchableActivity : MenuActivity() {
    private var databaseHelper: DatabaseHelper? = null
    private var database: SQLiteDatabase? = null
    private var cursor: Cursor? = null
    private var resultList: ListView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
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
            val toast = Toast.makeText(this, "error there!", Toast.LENGTH_SHORT)
            toast.show()
        }

    }

    /**
     * This method searches in the database to match the input given by the user.
     * @param input The search word or description input from the user
     * @return Cursor the cursor for finding the relevant information
     */

    fun searchInDatabase(input: String): Cursor? {
        databaseHelper = DatabaseHelper(this)
        database = databaseHelper!!.readableDatabase
        val columns = arrayOf("_id", "NAME", "DESCRIPTION")
        val selectionString = "NAME LIKE '%$input%' OR DESCRIPTION LIKE '%$input%'"
        //sort the filtered list in alphabetical order
        val orderBy = "NAME" + " ASC"
        cursor = database!!.query("NOODLE", columns, selectionString, null, null, null, orderBy)
        if (cursor != null) {
            cursor!!.moveToFirst()
        }
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
            resultList = findViewById(R.id.search_result_list)
            //create a new cursor adapter
            val adapter = SimpleCursorAdapter(this@SearchableActivity,
                android.R.layout.simple_list_item_1, cursor, arrayOf("NAME"), intArrayOf(android.R.id.text1), 0)
            //set the cursor adapter to the list view
            resultList!!.adapter = adapter

            // Define the on-click listener for the list items
            resultList!!.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                // Build the Intent used to open WordActivity with a specific word Uri
                val newIntent = Intent(this@SearchableActivity, DetailActivity::class.java)
                newIntent.putExtra(DetailActivity.CHOSEN_NOODLE_ITEM, id.toInt())
                startActivity(newIntent)
            }
        }
    }
}










