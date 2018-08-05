package com.danshima.noodleapp

import android.content.Intent
import android.content.res.Configuration
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem


class MainActivity : MenuActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var toggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showNavigationDrawer()

    }

    /**
     * This method is called by onCreate() to enable and display the toolbar and the navigation drawer
     */
    private fun showNavigationDrawer() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        //find the drawer view
        val menuDrawer = findViewById<DrawerLayout>(R.id.navigation_drawer)
        //add a drawer toggle for opening the navigation drawer. param: current activity, drawer layout, toolbar, string resources for open and close
        toggle = ActionBarDrawerToggle(
            this, menuDrawer, toolbar, R.string.toggle_drawer, R.string.toggle_off_drawer)
        //add the toggle to the drawer layout
        menuDrawer.addDrawerListener(toggle!!)
        //synchronize the icon to close or open

        //react to clicks on options in the drawer and calls onNavigationItemSelected
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener(this)

        //shows the content of the fragment in MainActivity's frame layout
        val fragment = CategoryFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.content_frame, fragment)
        transaction.commit()
    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state to close or open
        toggle!!.syncState()
    }

    override fun onConfigurationChanged(config: Configuration) {
        super.onConfigurationChanged(config)
        // Pass any configuration change to the drawer toggles
        toggle!!.onConfigurationChanged(config)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        selectOption(item)
        return true
    }

    /**
     * Navigation drawer options each carry out an action when clicked by the user
     */
    private fun selectOption(item: MenuItem) {
        //handle navigation view item clicks here
        val id = item.itemId
        val fragment = CategoryFragment()
        var intent: Intent? = null
        //create a bundle that holds information that can be retrieved from fragment
        val bundle = Bundle()

        //first parameter for bundle is the key of the bundle
        // category numbers are 1)Japanese 2)Vietnamese 3)Thai 4)Korean 5)Chinese
        when (id) {
            R.id.option_japanese -> {
                bundle.putInt("IDItem", 1)
                fragment.arguments = bundle
            }
            R.id.option_vietnamese -> {
                bundle.putInt("IDItem", 2)
                fragment.arguments = bundle
            }
            R.id.option_thai -> {
                bundle.putInt("IDItem", 3)
                fragment.arguments = bundle
            }
            R.id.option_korean -> {
                bundle.putInt("IDItem", 4)
                fragment.arguments = bundle
            }
            R.id.option_chinese -> {
                bundle.putInt("IDItem", 5)
                fragment.arguments = bundle
            }
            R.id.option_favorite -> {
                intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }
            R.id.option_newList -> {
                intent = Intent(this, LogActivity::class.java)
                startActivity(intent)
            }
        }

        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.content_frame, fragment).commit()
        } else {
            startActivity(intent)
        }
        val drawer = findViewById<DrawerLayout>(R.id.navigation_drawer)
        drawer.closeDrawer(GravityCompat.START)
    }


    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.navigation_drawer)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
