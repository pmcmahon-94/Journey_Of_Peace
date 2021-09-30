package com.example.journeyofpeace

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * Gives access to the activity_connolly layout.
 * Generates a video and sets a media controller anchored
 * to video for purpose of playing content.
 */
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