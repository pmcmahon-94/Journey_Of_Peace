package com.example.journeyofpeace

import android.content.Context
import android.content.ContextWrapper
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.maps.model.LatLng
import android.app.PendingIntent
import android.content.Intent
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.GeofenceStatusCodes

/**
 * Class helps build a Geofence and implements the ContextWrapper class
 *
 * @param base
 */
class GeofenceHelper(base: Context) : ContextWrapper(base) {

    private lateinit var pendingIntent: PendingIntent

    /**
     * Gets initial Geofence request
     * @param geofence
     */
    fun getGeofencingRequest(geofence: Geofence): GeofencingRequest {
        return GeofencingRequest.Builder()
            .addGeofence(geofence)
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .build()
    }

    /**
     * Builds the Geofence using the Builder class
     *
     * @param ID
     * @param latLng
     * @param radius
     * @param transitionTypes
     */
    fun getGeofence( ID: String, latLng: LatLng, radius: Float, transitionTypes: Int): Geofence {
        return Geofence.Builder()
            .setCircularRegion(latLng.latitude, latLng.longitude, radius)
            .setRequestId(ID)
            .setTransitionTypes(transitionTypes)
            .setLoiteringDelay(5000)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .build()
    }

    /**
     * Triggers [GeofenceBroadcastReceiver] using a pending intent as long as
     * pending intent is initialized
     */
    fun getPendingIntent(): PendingIntent {
        if(this::pendingIntent.isInitialized) {
            return pendingIntent
        }
        val intent = Intent(this, GeofenceBroadcastReceiver::class.java)
        pendingIntent =
            PendingIntent.getBroadcast(this, 2607, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        return pendingIntent
    }

    /**
     * Generates error message for any possible exceptions
     * @throws e
     */
    fun getErrorString(e: Exception): String? {
        if (e is ApiException) {
            when (e.statusCode) {
                GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE -> return "GEOFENCE_NOT_AVAILABLE"
                GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES -> return "GEOFENCE_TOO_MANY_GEOFENCES"
                GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS -> return "GEOFENCE_TOO_MANY_PENDING_INTENTS"
            }
        }
        return e.localizedMessage
    }
}