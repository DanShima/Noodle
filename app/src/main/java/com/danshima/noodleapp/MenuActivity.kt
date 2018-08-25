package com.danshima.noodleapp

import android.app.SearchManager
import android.app.SearchableInfo
import android.content.Context
import android.content.Intent
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.support.v7.widget.ShareActionProvider


open class MenuActivity : AppCompatActivity() {
    lateinit var share: ShareActionProvider
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    /**
     * This method enables menu option in the toolbar (in this case, the search option only)
     * @param menu The menu option clicked by the user
     * @return true if clicked
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //inflate menu resource file
        menuInflater.inflate(R.menu.menu_toolbar, menu)

        //use the searchManager to find the searchableInfo related to this activity
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        //connect the activity's SearchableInfo to the toolbar's search option
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        val searchableInfo = searchManager.getSearchableInfo(componentName)
        searchView.setSearchableInfo(searchableInfo)

        val item = menu.findItem(R.id.option_share)
        //fetch reference to the share action provider
        share = MenuItemCompat.getActionProvider(item) as ShareActionProvider
        setShareIntent()

        //display menu
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * This method starts simple sharing process to other texting apps.
     * It doesn't share any content of the app.
     */
    private fun setShareIntent() {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Wanna eat out with me tonight?")
        shareIntent.type = "text/plain"
        share.setShareIntent(shareIntent)
    }


    /**
     * This method activates the activity's reaction when an action in the toolbar is clicked
     * @param item The MenuItem object that is the action on the toolbar that was clicked by the user
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_search) {
            onSearchRequested()
        }
        return true
    }
}