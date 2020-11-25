package br.com.battlepassCalculatorValorant.ui.notification

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.ui.activity.MainActivity


class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent: PendingIntent = TaskStackBuilder.create(context)
            .addNextIntent(intent)
            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, "notifyLemubit")
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle("Ei Psciu!! Hora de atualizar seu passe, hein?!")
            .setContentText("Atualize os dados do passe, para se manter informado!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(200, builder.build())
    }
}