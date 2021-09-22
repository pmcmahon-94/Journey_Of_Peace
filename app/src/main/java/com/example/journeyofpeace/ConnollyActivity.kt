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
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class ConnollyActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connolly)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val urlString = "android.resource://com.example.journeyofpeace/" + R.raw.easter_rising

        val videoView = findViewById<VideoView>(R.id.connolly_video)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        val videoURI = Uri.parse(urlString)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(videoURI)
    }
}