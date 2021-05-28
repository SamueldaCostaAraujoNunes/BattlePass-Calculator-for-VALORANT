package br.com.samuelnunes.valorantpassbattle.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import br.com.samuelnunes.valorantpassbattle.BuildConfig

class Notification(val context: Context) {
    init {
        NotificationChannel(context).create()
    }

    fun create() {
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val timeAtButtonClick = System.currentTimeMillis()
        val day = 1000 * 60 * 60 * 24
        val gameDuration = 1000 * 60 * 50
        val duration = if (BuildConfig.DEBUG) {
            (1000 * 15).toLong()
        } else {
            (day - gameDuration).toLong()
        }

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            timeAtButtonClick + duration,
            pendingIntent
        )
    }
}