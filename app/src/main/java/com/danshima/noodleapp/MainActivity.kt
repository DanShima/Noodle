package com.danshima.noodleapp

import android.content.Intent
import android.content.res.Configuration
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*


class MainActivity : MenuActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupBottomNavigation()
        showNavigationDrawer()
        startMainFragment()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    Toast.makeText(this, "home", Toast.LENGTH_SHORT).show()
                }
                R.id.action_favorite -> {
                    intent = Intent(this, FavoriteActivity::class.java)
                    startActivity(intent)
                }
                R.id.action_account -> {
                    Toast.makeText(this, "account", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
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
        supportFragmentManager.beginTransaction().add(R.id.main_frame_holder, fragment).commit()
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
        supportFragmentManager.beginTransaction().replace(R.id.main_frame_holder, fragment).commit()
    }


    override fun onBackPressed() {
        if (navigation_drawer.isDrawerOpen(GravityCompat.START)) {
            navigation_drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
