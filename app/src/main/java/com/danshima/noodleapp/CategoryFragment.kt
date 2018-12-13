package com.danshima.noodleapp

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * A subclass displayed in the main page that shows a list of noodle per category.
 */
class CategoryFragment : androidx.fragment.app.Fragment() {
    private lateinit var cursor: Cursor
    private lateinit var database: SQLiteDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_category, container, false)

        val databaseHelper = DatabaseHelper(requireContext())
        val noodleListPerCategory = view.findViewById<ListView>(R.id.category_noodleList)

        try {
            database = databaseHelper.readableDatabase
            fetchCategory()

            //create the cursor adapter to fill the list view with values from the database
            val listAdapter = SimpleCursorAdapter(context, android.R.layout.simple_list_item_1, cursor, arrayOf("NAME"),
                intArrayOf(android.R.id.text1), 0)
            noodleListPerCategory.adapter = listAdapter
        } catch (e: SQLiteException) {
            e.printStackTrace()
            Toast.makeText(context, "Database is not working!", Toast.LENGTH_SHORT).show()
        }

        //show item detail using the listener when an item is clicked
        val itemClickListener = AdapterView.OnItemClickListener { _, _, _, id ->
            //starts DetailActivity
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.CHOSEN_NOODLE_ITEM, id.toInt())
            startActivity(intent)
        }
        //connects the listener to the list view
        noodleListPerCategory.onItemClickListener = itemClickListener
        return view
    }

    /**
     * This method is called when the database and cursor need to be closed.
     * They are closed when the cursor adapter doesn't need them anymore.
     */
    override fun onDestroy() {
        super.onDestroy()
        cursor.close()
        database.close()
    }

    /**
     * This method gets data from the category column in the database
     * @param
     * @return The cursor used to perform database query
     */
    private fun fetchCategory(): Cursor? {
        //the Integer value stored in the category column in the database
        var iID = 0
        //we use a bundle to store the data
        val bundle = this.arguments
        if (bundle != null) {
            iID = bundle.getInt("IDItem", 1)
        }
        val selectQuery = "SELECT * FROM NOODLE WHERE CATEGORY=$iID"
        cursor = database.rawQuery(selectQuery, null)
        cursor.moveToFirst()
        return cursor
    }

    companion object {
        @JvmStatic
        fun newInstance() = CategoryFragment()
    }
}
















