package com.danshima.noodleapp;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
 private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showNavigationDrawer();

    }

    /**
     * This method is called by onCreate() to enable and display the toolbar and the navigation drawer
     * */

    private void showNavigationDrawer() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //find the drawer view
        DrawerLayout menuDrawer = findViewById(R.id.navigation_drawer);
        //add a drawer toggle for opening the navigation drawer. param: current activity, drawer layout, toolbar, string resources for open and close
        toggle = new ActionBarDrawerToggle(
                this, menuDrawer, toolbar, R.string.toggle_drawer, R.string.toggle_off_drawer);
        //add the toggle to the drawer layout
        menuDrawer.addDrawerListener(toggle);
        //synchronize the icon to close or open
        //toggle.syncState(); <-- moved to a separate method

        //react to clicks on options in the drawer and calls onNavigationItemSelected
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //shows the content of the fragment in MainActivity's frame layout
        Fragment fragment = new JapaneseFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_frame, fragment);
        transaction.commit();
    }





    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state to close or open
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        // Pass any configuration change to the drawer toggles
       toggle.onConfigurationChanged(config);

    }

    /**
     * this method is called when the user clicks on an option in the drawer. it shows the selected option after clicking.
     * @param item the clicked menu option
     * @return boolean. highlight the item or not
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        selectOption(item);
        return true;
    }


    /**
     * Navigation drawer options each carry out an action when clicked by the user
     * @param item the drawer menu option that was clicked
     * */

       private void selectOption(MenuItem item){
        //handle navigation view item clicks here
        int id = item.getItemId();
        //Fragment fragment = null;
        Fragment fragment = new JapaneseFragment();
        Intent intent = null;
        //create a bundle that holds information that can be retrieved from fragment
        Bundle bundle = new Bundle();

        //category numbers are 1)Japanese 2)Vietnamese 3)Thai 4)Korean 5)Chinese
        switch(id) {
            //case R.id.action_search:
            case R.id.option_japanese:
                bundle.putInt("IDItem", 1);
                fragment.setArguments(bundle);
                break;
            case R.id.option_vietnamese:
                bundle.putInt("IDItem", 2);
                fragment.setArguments(bundle);
                break;
            case R.id.option_thai:
                bundle.putInt("IDItem", 3);
                fragment.setArguments(bundle);
                break;
            case R.id.option_korean:
                bundle.putInt("IDItem", 4);
                fragment.setArguments(bundle);
                break;
            case R.id.option_chinese:
                bundle.putInt("IDItem", 5);
                fragment.setArguments(bundle);
                break;
            case R.id.option_favorite:
                intent = new Intent(this, FavoriteActivity.class);
                startActivity(intent);
                break;
                //TODO implement share and show something in the start page
                //for "share to" option

//                intent = new Intent(this, DetailActivity.class);
//                break;
            default:
                //super.onOptionsItemSelected(item);
                //intent = new Intent(this, NoodleActivity.class);
                //fragment = new JapaneseFragment();
                intent = new Intent(MainActivity.this, NoodleActivity.class);
                intent.putExtra(DetailActivity.CHOSEN_NOODLE_ITEM, (int) id);
                startActivity(intent);
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment).commit();
        } else {
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.navigation_drawer);
        drawer.closeDrawer(GravityCompat.START);

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
        //locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.action_search);
        //use the searchManager to find the searchableInfo related to this activity
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        //connect the activity's SearchableInfo to the toolbar's search option
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());

        searchView.setSearchableInfo(searchableInfo);
        //searchView.setIconifiedByDefault(false);
        //display menu
        return true;
        //return super.onCreateOptionsMenu(menu);
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_search) {
            onSearchRequested();
        }
        return true;

        /*switch (item.getItemId()) {
            case R.id.action_search:
               onSearchRequested();
                return true;
            default:
                return false;
        }*/
    }


    //close the drawer when the Back button is clicked if it's open
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.navigation_drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
