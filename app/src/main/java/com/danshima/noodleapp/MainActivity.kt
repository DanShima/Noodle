package com.danshima.noodleapp

import android.content.Intent
import android.content.res.Configuration
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*


class MainActivity : MenuActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        showNavigationDrawer()
        startMainFragment()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    /**
     * This method is called by onCreate() to enable and display the toolbar and the navigation drawer
     */
    private fun showNavigationDrawer() {
        toggle = ActionBarDrawerToggle(
            this, navigation_drawer, toolbar, R.string.toggle_drawer, R.string.toggle_off_drawer)
        navigation_drawer.addDrawerListener(toggle)
        navigation_view.setNavigationItemSelectedListener(this)
    }

    private fun startMainFragment() {
        val fragment = CategoryFragment.newInstance()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.content_frame, fragment)
        transaction.commit()
    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(config: Configuration) {
        super.onConfigurationChanged(config)
        toggle.onConfigurationChanged(config)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navigation_drawer.closeDrawer(GravityCompat.START)
        selectOption(item)
        return true
    }

    /**
     * Navigation drawer options each carry out an action when clicked by the user
     */
    private fun selectOption(item: MenuItem) {
        val fragment = CategoryFragment.newInstance()
        val intent: Intent
        val bundle = Bundle()
        // category numbers are 1)Japanese 2)Vietnamese 3)Thai 4)Korean 5)Chinese
        when (item.itemId) {
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
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit()
    }


    override fun onBackPressed() {
        if (navigation_drawer.isDrawerOpen(GravityCompat.START)) {
            navigation_drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
