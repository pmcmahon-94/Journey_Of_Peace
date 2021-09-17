package com.example.journeyofpeace

import android.content.Context
import android.content.ContextWrapper
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationCompat
import android.app.Notification
import android.content.Intent
import android.app.NotificationManager
import android.app.NotificationChannel
import android.app.PendingIntent.*
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

class NotificationHelper(base: Context) : ContextWrapper(base) {

    private val TAG = "NotificationHelper"

    private val CHANNEL_NAME = "High priority channel"
    private val CHANNEL_ID = "com.example.notifications$CHANNEL_NAME"

    init {
        createChannels()
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun createChannels() {
        val notificationChannel =
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.enableLights(true)
        notificationChannel.enableVibration(true)
        notificationChannel.description = "this is the description of the channel."
        notificationChannel.lightColor = Color.RED
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(notificationChannel)
    }

    fun sendHighPriorityNotification(title: String?, body: String?, activityName: Class<*>?) {
        val intent = Intent(this, activityName)
        val pendingIntent =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                getActivity(this, 267, intent, FLAG_UPDATE_CURRENT)
            } else {
                TODO("VERSION.SDK_INT < S")
            }
        val notification: Notification =
            NotificationCompat.Builder(this, CHANNEL_ID)
                //.setContentTitle(title)
                //.setContentText(body)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(NotificationCompat.BigTextStyle().setSummaryText("summary")
                    .setBigContentTitle(title).bigText(body))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()
        NotificationManagerCompat.from(this).notify(Random().nextInt(), notification)
    }
}