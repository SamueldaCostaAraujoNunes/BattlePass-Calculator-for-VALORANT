package br.com.battlepassCalculatorValorant.ui.view.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat


class NotificationChannel(val context: Context) {

    companion object {
        const val name = "battlepassCalculatorValorant.notifyChannel"
        const val description = "Channel for Battle Pass Calculator for Valorant"
        const val importance = NotificationManager.IMPORTANCE_DEFAULT
        const val id = "notifyChannel"
    }

    fun create() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(id, name, importance)
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