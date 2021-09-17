package com.example.journeyofpeace

import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import android.net.Uri
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class ConnollyActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connolly)

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

        val urlString = "android.resource://com.example.journeyofpeace/" + R.raw.easter_rising

        val videoView = findViewById<VideoView>(R.id.connolly_video)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        val videoURI = Uri.parse(urlString)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(videoURI)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}