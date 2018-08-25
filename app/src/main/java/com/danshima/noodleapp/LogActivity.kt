package com.danshima.noodleapp

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import java.util.ArrayList
import java.util.Collections
import java.util.Comparator
import java.util.HashSet

/**
 * This activity class allows user to quickly note down their experience or a noodle dish they have tried.
 */


class LogActivity : MenuActivity() {
    private var log: UserLog? = null
    private var adapter: ArrayAdapter<String>? = null
    private var experiences: ArrayList<String>? = null

    /**
     * This method retrieves the data stored through SharedPreferences.
     * @return the stored ArrayList
     */
    private//if shared preference is null, the method return empty Hashset and not null
    //HashSet gives us an unsorted arraylist so we need to sort it again before returning it.
    val array: ArrayList<String>
        get() {
            val sp = this.getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE)
            val set = sp.getStringSet("list", HashSet())
            experiences = ArrayList(set!!)
            experiences!!.sort()
            return experiences as ArrayList<String>
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        //set up toolbar as the normal app bar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "My List"
        setSupportActionBar(toolbar)

        //get references to layout widgets
        val button = findViewById<Button>(R.id.add_to_log)
        val listView = findViewById<ListView>(R.id.newLog)

        experiences = ArrayList()
        experiences = array
        //create an array adapter that connects the array to the list view
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, experiences!!)

        //enable listener so user input is registered when the button is clicked
        val listener = object: View.OnClickListener {
            override fun onClick(v: View) {
                val enterLog = findViewById<EditText>(R.id.enterLog)
                val input = enterLog.text.toString()
                //if the user doesn't enter any text, a toast will be shown
                if (input.length == 0) {
                    val toastError = Toast.makeText(this@LogActivity, "Write something!", Toast.LENGTH_SHORT)
                    toastError.show()
                } else {
                    try {
                        //add the new experience to the arrayList
                        addLog(input)
                        adapter!!.notifyDataSetChanged()
                        enterLog.setText("")
                    } catch (e: Exception) {
                        e.printStackTrace()
                        val toastError = Toast.makeText(this@LogActivity, "Cannot be added", Toast.LENGTH_SHORT)
                        toastError.show()
                    }

                }
            }
        }
        button.setOnClickListener(listener)
        listView.adapter = adapter
        listView.dividerHeight = 3

        //if the user clicks an item in the list view long enough, the item is removed
        listView.onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, position, arg ->
            removeLog(position)
            adapter!!.notifyDataSetChanged()
            false
        }
    }

    /**
     * This method stores the data in the arrayList "experiences" through Shared Preferences,
     * as it otherwise disappears when you close the app or go to another activity.
     * @return true if stored in an editor object
     */
    private fun saveArray(): Boolean {
        //enter key and mode(the file can only be accessed using calling app)
        val sp = this.getSharedPreferences(SHARED_PREFS_NAME, Activity.MODE_PRIVATE)
        val editor = sp.edit()
        val set = HashSet<String>()
        set.addAll(experiences!!)
        editor.putStringSet("list", set)
        return editor.commit()
    }

    /**
     * When the activity stops, saveArray() is called to do its work so no data is lost
     */
    public override fun onDestroy() {
        saveArray()
        super.onDestroy()
    }

    /**
     * This method adds a new experience or log (user input) to the ArrayList
     * @param newExp the experience/text entered
     */
    fun addLog(newExp: String) {
        var newExp = newExp
        //convert the UserLog object into string for the arrayList
        log = UserLog(newExp)
        newExp = log!!.toString()
        experiences!!.add(0, newExp)
    }

    /**
     * This method removes an entry from the log list if the user long clicks it.
     * @param position the index of the entry
     */
    fun removeLog(position: Int) {
        val log = experiences!![position]
        experiences!!.remove(log)
    }

    companion object {
        private val SHARED_PREFS_NAME = "MY_SHARED_PREF"
    }

}
