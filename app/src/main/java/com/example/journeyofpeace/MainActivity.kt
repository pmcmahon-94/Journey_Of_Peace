package com.example.journeyofpeace

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

/**
 * Class triggers the main view of the app.
 * Navigation drawer is initialized. Buttons added and initialized to activate
 * other activities in the app.
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onStart()

        //create toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.nav_view)
        val Toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(Toggle)
        Toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

        val journey = findViewById<View>(R.id.journey) as Button
        val locations = findViewById<View>(R.id.locations) as Button

        journey.setOnClickListener {
            Toast.makeText(this@MainActivity,
                "journey",
                Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        locations.setOnClickListener {
            Toast.makeText(this@MainActivity,
                "locations",
                Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
        }

    }

    /**
     * Takes each item from the navigation drawer and starts an activity
     * @param item
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        //add some event to the menu
        when (item.itemId) {
            R.id.Home -> {
                Toast.makeText(baseContext, "home", Toast.LENGTH_SHORT).show()
                val intentHome = Intent(this, MainActivity::class.java)
                startActivity(intentHome)
            }
            R.id.about_us -> {
                Toast.makeText(baseContext, "about", Toast.LENGTH_SHORT).show()
                val intentAbout = Intent(this, AboutActivity::class.java)
                startActivity(intentAbout)
            }
            R.id.contact_us -> {
                Toast.makeText(baseContext, "contact", Toast.LENGTH_SHORT).show()
                val intentAbout = Intent(this, ContactActivity::class.java)
                startActivity(intentAbout)
            }
        }
        return true
    }
}