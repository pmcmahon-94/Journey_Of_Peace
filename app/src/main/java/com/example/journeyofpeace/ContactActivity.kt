package com.example.journeyofpeace

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class ContactActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var toolbar: Toolbar
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var myMap: GoogleMap

    private val failteFeirste = LatLng(54.592120, -5.962520)

    private lateinit var markerFFT: Marker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        //create toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val email = findViewById<View>(R.id.email_text)
        val phone = findViewById<View>(R.id.phone_text)

        email.setOnClickListener {
            Toast.makeText(this@ContactActivity,
                "email",
                Toast.LENGTH_SHORT).show()
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "text/plain"
            startActivity(emailIntent)
        }

        phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL,
                Uri.parse("tel:" + "Your Phone_number"))
            startActivity(intent)
        }

        mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        myMap = googleMap

        markerFFT = myMap.addMarker(
            MarkerOptions()
                .position(failteFeirste)
                .title("Failte Feirste Thiar")
        )
        markerFFT.tag = 0

    }
}