package com.example.journeyofpeace

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent

/**
 * Class generates an instance of NotificationHelper and GeofencingEvent that triggers
 * a notification to user informing of location being close in proximity
 */
class GeofenceBroadcastReceiver : BroadcastReceiver() {

    private val TAG = "GeofenceBroadcastReceiver"

    /**
     * This function is called when the BroadcastReceiver is receiving an Intent broadcast.
     */
    override fun onReceive(context: Context, intent: Intent) {

        val notificationHelper = NotificationHelper(context)
        val geofencingEvent = GeofencingEvent.fromIntent(intent)

        if (geofencingEvent.hasError()) {
            Log.d(TAG, "onReceive: Error receiving geofence event...")
            return
        }

        val geofenceList = geofencingEvent.triggeringGeofences
        for (geofence: Geofence in geofenceList) {
            Log.d(TAG, "onReceive: " + geofence.requestId)
        }

        // Unit test that this is true and false
        when (geofencingEvent.geofenceTransition) {
            Geofence.GEOFENCE_TRANSITION_ENTER -> {
                Toast.makeText(context, "GEOFENCE_TRANSITION_ENTER", Toast.LENGTH_SHORT).show()
                notificationHelper.sendHighPriorityNotification("Aras Ui Chonghaile", "You are near the James Connolly centre. click below to view information on the iconic historic figure",
                    ConnollyActivity::class.java)
            }
            Geofence.GEOFENCE_TRANSITION_DWELL -> {
                Toast.makeText(context, "GEOFENCE_TRANSITION_DWELL", Toast.LENGTH_SHORT).show()
                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_DWELL", "",
                    MapActivity::class.java)
            }
            Geofence.GEOFENCE_TRANSITION_EXIT -> {
                Toast.makeText(context, "GEOFENCE_TRANSITION_EXIT", Toast.LENGTH_SHORT).show()
                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_EXIT",
                    "",
                    MapActivity::class.java)
            }
        }
    }
}