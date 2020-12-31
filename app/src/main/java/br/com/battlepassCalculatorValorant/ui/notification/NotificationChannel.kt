package br.com.battlepassCalculatorValorant.ui.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat

class NotificationChannel(val context: Context) {

    companion object {
        val name = "battlepassCalculatorValorant.notifyChannel"
        val description = "Channel for Battle Pass Calculator for Valorant"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val id = "notifyChannel"
    }

    fun create() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("notifyChannel", name, importance)
            channel.description = description

            val notificationManager =
                ContextCompat.getSystemService(
                    context,
                    NotificationManager::class.java
                ) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}