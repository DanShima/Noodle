package com.danshima.noodleapp;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.ShareActionProvider;



public class MenuActivity extends AppCompatActivity {
 ShareActionProvider share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * This method enables menu option in the toolbar (in this case, the search option only)
     * @param menu The menu option clicked by the user
     * @return true if clicked
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu resource file
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        //use the searchManager to find the searchableInfo related to this activity
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        //connect the activity's SearchableInfo to the toolbar's search option
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
        searchView.setSearchableInfo(searchableInfo);

        MenuItem item = menu.findItem(R.id.option_share);
        //fetch reference to the share action provider
        share = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        setShareIntent();

        //display menu
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * This method starts simple sharing process to other texting apps.
     * It doesn't share any content of the app.
     */
    private void setShareIntent() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Wanna eat out with me tonight?");
        shareIntent.setType("text/plain");
        share.setShareIntent(shareIntent);
    }


    /**
     * This method activates the activity's reaction when an action in the toolbar is clicked
     * @param item The MenuItem object that is the action on the toolbar that was clicked by the user
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_search) {
            onSearchRequested();
        }
        return true;
    }
}