package com.danshima.noodleapp

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.app.NavUtils
import androidx.appcompat.app.ActionBar
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.view.View
import android.widget.CheckBox
import android.content.ContentValues
import com.danshima.noodleapp.DetailActivity.Companion.CHOSEN_NOODLE_ITEM
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : MenuActivity() {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var database: SQLiteDatabase
    private lateinit var cursor: Cursor

    //get the noodle that was selected from the intent
    private val noodleItemID: Int
        get() = intent.extras!!.get(CHOSEN_NOODLE_ITEM) as Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        //get a support Actionbar corresponding to this toolbar
        val ab = supportActionBar
        //enables the Up button
        ab!!.setDisplayHomeAsUpEnabled(true)
        populateNoodleDetails()
    }

    /**
     * This method fetches data from the SQLite database and connects the data to the view.
     */
    private fun populateNoodleDetails() {
        //create a cursor
        databaseHelper = DatabaseHelper(this)
        try {
            database = databaseHelper.readableDatabase
            cursor = database.query("NOODLE", arrayOf("NAME", "DESCRIPTION", "IMAGEID", "RESTAURANT", "FAVORITE"),
                "_id = ?", arrayOf(Integer.toString(noodleItemID)), null, null, null)

        } catch (e: SQLiteException) {
            e.printStackTrace()
            val toastError = Toast.makeText(this, "Database error!!", Toast.LENGTH_SHORT)
            toastError.show()
        }

        //move to the first row in the Cursor
        if (cursor.moveToFirst()) {
            //get details from the cursor
            val name = cursor.getString(0)
            val description = cursor.getString(1)
            val image = cursor.getInt(2)
            val restaurant = cursor.getString(3)
            // 1 for checked, and 0 for unchecked in the favorite checkbox
            val isFavorite = cursor.getInt(4) == 1

            name_info.text = name

            image_info.setImageResource(image)
            image_info.contentDescription = name

            description_info.text = description
            restaurant_info.text = restaurant
            add_to_favorite_btn.isChecked = isFavorite
        }
    }

    /**
     * update the column favorite in the database when the checkbox is clicked
     * @param view The "save to favorite" CheckBox
     */
    fun saveFavorite(view: View) {
        //Get the value of the checkbox
        val favorite = findViewById<CheckBox>(R.id.add_to_favorite_btn)
        val isFavorite = favorite.isChecked
        val noodleValues = ContentValues()
        noodleValues.put("FAVORITE", isFavorite)
        //Get a reference to the database and update the FAVORITE column
        databaseHelper = DatabaseHelper(this)
        try {
            database = databaseHelper.writableDatabase
            database.update("NOODLE", noodleValues, "_id = ?", arrayOf(Integer.toString(noodleItemID)))
        } catch (e: SQLiteException) {
            val toastError = Toast.makeText(this, "Database error!!!", Toast.LENGTH_SHORT)
            toastError.show()
        }

    }

    /**
     * Need to override MenuActivity's onOptionsItemSelected method because the Up button doesn't work otherwise.
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

    companion object {
        //the ID of the noodles that is used to get details of the noodle the user selected
        const val CHOSEN_NOODLE_ITEM = "noodleItem"
    }
}
