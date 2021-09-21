package com.example.journeyofpeace

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.journeyofpeace.PermissionUtils.PermissionDeniedDialog.Companion.newInstance
import com.example.journeyofpeace.PermissionUtils.isPermissionGranted
import com.example.journeyofpeace.PermissionUtils.requestPermission
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.location.Geofence

class MapActivity: AppCompatActivity(), GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener, OnMapReadyCallback,
    ActivityCompat.OnRequestPermissionsResultCallback {

    private lateinit var toolbar: Toolbar
    private lateinit var myMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment
    lateinit var geofencingClient: GeofencingClient
    lateinit var geofenceHelper: GeofenceHelper
    private val TAG = "MapsActivity"

    private val GEOFENCE_RADIUS = 60
    private val GEOFENCE_RADIUS_CEMETERY = 1613

    private val GEOFENCE_ID = "SOME_GEOFENCE_ID"

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * [.onRequestPermissionsResult].
     */
    private var permissionDenied = false

    private val bSands = LatLng(54.5979, -5.9528)
    private val connCentre = LatLng(54.5902, -5.9678)
    private val clonMonastery = LatLng(54.6000, -5.9571)
    private val millCemetary = LatLng(54.5828, -5.9738)
    private val muralWall = LatLng(54.6010, -5.9564)
    private val radioFailte = LatLng(54.5998, -5.9402)

    private lateinit var markerSands: Marker
    private lateinit var markerCentre: Marker
    private lateinit var markerMonastery: Marker
    private lateinit var markerCemetary: Marker
    private lateinit var markerWall: Marker
    private lateinit var markerRadio: Marker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        //create toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        geofencingClient = LocationServices.getGeofencingClient(this)
        geofenceHelper = GeofenceHelper(this)

    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (!::myMap.isInitialized) return
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            myMap.isMyLocationEnabled = true
        } else {
            // Permission to access the location is missing. Show rationale and request permission
            requestPermission(this, FINE_LOCATION_PERMISSION_REQUEST_CODE,
                Manifest.permission.ACCESS_FINE_LOCATION, true
            )
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show()
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false
    }

    override fun onMyLocationClick(location: Location) {
        Toast.makeText(this, "Current location:\n$location", Toast.LENGTH_LONG).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode != FINE_LOCATION_PERMISSION_REQUEST_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }
        if (isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation()
        } else {
            // Permission was denied. Display an error message
            // Display the missing permission error dialog when the fragments resume.
            permissionDenied = true
        }
        if (requestCode == BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE) {
            if (isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                //We have the permission
                Toast.makeText(this, "You can add geofences...", Toast.LENGTH_SHORT).show()
            } else {
                //We do not have the permission..
                Toast.makeText(this, "Background location access is neccessary for geofences to trigger...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (permissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError()
            permissionDenied = false
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private fun showMissingPermissionError() {
        newInstance(true).show(supportFragmentManager, "dialog")
    }

    /**
     * When the map is ready for use, markers will appear for each of the locations
     * defined below. The locations are initialised using their Longitude and Latitude.
     * Markers are then set up for each location with a title appearing when user taps
     * on the marker within the map fragment.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        myMap = googleMap

        googleMap.setOnMyLocationButtonClickListener(this)
        googleMap.setOnMyLocationClickListener(this)
        enableMyLocation()

        // Add circle to marker with radius of 50
        createCircle(latLng = radioFailte, GEOFENCE_RADIUS)
        createCircle(latLng = muralWall, GEOFENCE_RADIUS)
        createCircle(latLng = bSands, GEOFENCE_RADIUS)
        createCircle(latLng = clonMonastery, GEOFENCE_RADIUS)
        createCircle(latLng = connCentre, GEOFENCE_RADIUS)
        createCircle(latLng = millCemetary, GEOFENCE_RADIUS_CEMETERY)

        // Add Geofence using same radius as circle to each location
        addGeofence(latLng = radioFailte, GEOFENCE_RADIUS)
        addGeofence(latLng = muralWall, GEOFENCE_RADIUS)
        addGeofence(latLng = bSands, GEOFENCE_RADIUS)
        addGeofence(latLng = clonMonastery, GEOFENCE_RADIUS)
        addGeofence(latLng = connCentre, GEOFENCE_RADIUS)
        addGeofence(latLng = millCemetary, GEOFENCE_RADIUS_CEMETERY)

        // Add some markers to the map, and add a data object to each marker.
        markerRadio = myMap.addMarker(
            MarkerOptions()
                .position(radioFailte)
                .title("Radio Failte")
        )
        markerRadio.tag = 0
        markerWall = myMap.addMarker(
            MarkerOptions()
                .position(muralWall)
                .title("Solidarity Wall")
        )
        markerWall.tag = 0
        markerSands = myMap.addMarker(
            MarkerOptions()
                .position(bSands)
                .title("Bobby Sands Mural")
        )
        markerSands.tag = 0
        markerMonastery = myMap.addMarker(
            MarkerOptions()
                .position(clonMonastery)
                .title("Clonard Monastery")
        )
        markerMonastery.tag = 0
        markerCentre = myMap.addMarker(
            MarkerOptions()
                .position(connCentre)
                .title("Aras Ui Chongaile")
        )
        markerCentre.tag = 0
        markerCemetary = myMap.addMarker(
            MarkerOptions()
                .position(millCemetary)
                .title("Milltown Cemetery")
        )
        markerCemetary.tag = 0
    }

    private fun createCircle(latLng: LatLng, radius: Int){
        val circleOptions = CircleOptions()
        circleOptions.center(latLng)
        circleOptions.radius(radius.toDouble())
        circleOptions.strokeColor(Color.argb(255, 0, 255, 0))
        circleOptions.fillColor(Color.argb(64, 0, 255, 0))
        circleOptions.strokeWidth(4F)
        myMap.addCircle(circleOptions)
    }

    @SuppressLint("MissingPermission")
    private fun addGeofence(latLng: LatLng, radius: Int) {
        val geofence = geofenceHelper.getGeofence(GEOFENCE_ID,
            latLng,
            radius.toFloat(),
            Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_DWELL or Geofence.GEOFENCE_TRANSITION_EXIT)
        val geofencingRequest = geofenceHelper.getGeofencingRequest(geofence)
        val pendingIntent = geofenceHelper.getPendingIntent()

        geofencingClient.addGeofences(geofencingRequest, pendingIntent).run {
            addOnSuccessListener { Log.d(TAG, "onSuccess: Geofence Added...") }
            addOnFailureListener { e ->
                val errorMessage: String? = geofenceHelper.getErrorString(e)
                Log.d(TAG, "onFailure: $errorMessage")
            }
        }


    }

    companion object {
        /**
         * Request code for location permission request.
         *
         * @see .onRequestPermissionsResult
         */
        private const val FINE_LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE = 0
    }
}