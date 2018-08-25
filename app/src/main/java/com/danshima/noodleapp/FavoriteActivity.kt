package com.danshima.noodleapp

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.support.v4.app.NavUtils
import android.support.v7.app.ActionBar
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.CursorAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter

class FavoriteActivity : MenuActivity() {
    private var favoritesCursor: Cursor? = null
    private var database: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "My Favorites"
        setSupportActionBar(toolbar)
        //get a support Actionbar corresponding to this toolbar
        val ab = supportActionBar
        //enables the Up button
        ab!!.setDisplayHomeAsUpEnabled(true)
        showFavoriteList()
        populateFavoriteList()

    }

    /**
     * This method is called when the user clicks on an item in the favorite list and directs the user to the detailed noodle page.
     */
    private fun showFavoriteList() {
        val itemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            //if the noodle option in the list view is clicked, start DetailActivity
            val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
            startActivity(intent)
        }
        //add the listener to the list view
        val listview = findViewById<ListView>(R.id.favorite_noodleList)
        listview.onItemClickListener = itemClickListener
    }

    /**
     * populate the favorite_noodleList list view with user's favorite noodles.
     */
    private fun populateFavoriteList() {
        val favoriteList = findViewById<ListView>(R.id.favorite_noodleList)
        //get reference to the database
        val databaseHelper = DatabaseHelper(this)
        database = databaseHelper.readableDatabase
        //create a cursor that gets the values of the _id and NAME columns when FAVORITE = 1
        favoritesCursor = database!!.query("NOODLE", arrayOf("_id", "NAME"), "FAVORITE = 1", null, null, null, null)


        //create a new cursor adapter
        val favoriteAdapter = SimpleCursorAdapter(this@FavoriteActivity,
            android.R.layout.simple_list_item_1, favoritesCursor, arrayOf("NAME"), intArrayOf(android.R.id.text1), 0)
        //set the cursor adapter to the list view
        favoriteList.adapter = favoriteAdapter
        //navigate to DetailActivity if a noodle is clicked
        favoriteList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
            //need to include the ID of the noodle specifically!
            intent.putExtra(DetailActivity.CHOSEN_NOODLE_ITEM, id.toInt())
            startActivity(intent)
        }
    }


    /**
     * this method is called when user goes back to FavoriteActivity after adding favorites.
     * we need a new cursor because new added favorites are not shown while the current cursor is still active
     */
    override fun onRestart() {
        super.onRestart()
        //new favorites are not shown in the list. create a new cursor
        try {
            val newCursor = database!!.query("NOODLE", arrayOf("_id", "NAME"), "FAVORITE = 1", null, null, null, null)
            //get reference to the list view's cursor adapter
            val listFavorites = findViewById<ListView>(R.id.favorite_noodleList)
            val adapter = listFavorites.adapter as CursorAdapter
            //change the current cursor to the new cursor
            adapter.changeCursor(newCursor)
            favoritesCursor = newCursor
        } catch (e: SQLiteException) {
            e.printStackTrace()
        }

    }

    /**
     * Close the cursor and database afterward
     */
    public override fun onDestroy() {
        super.onDestroy()
        favoritesCursor!!.close()
        database!!.close()
    }


    /**
     * Need to override MenuActivity's onOptionsItemSelected method because the Up button otherwise doesn't work.
     * @param item The MenuItem object that is the action on the toolbar that was clicked by the user
     * @return true if clicked
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            android.R.id.home -> NavUtils.navigateUpFromSameTask(this)
        }
        return true
    }


}
