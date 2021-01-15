package br.com.battlepassCalculatorValorant.ui.notification

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.SingletonPassBattle.ManagerProperties
import br.com.battlepassCalculatorValorant.ui.activity.MainActivity
import kotlin.math.absoluteValue


class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val listNotificationContent = createListNotificationContents(context)
        val pendingIntent: PendingIntent = createIntent(context)
        val builder = builderNotification(context, pendingIntent, listNotificationContent.random())
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(0, builder.build())
    }

    private fun createListNotificationContents(context: Context): List<NotificationContent> {
        val properties = ManagerProperties.getInstance(context)

        return listOf(
            NotificationContent(
                context.getString(R.string.notification_title_1),
                context.getString(R.string.notification_content_1)
            ),
            NotificationContent(
                context.getString(R.string.notification_title_2),
                context.getString(R.string.notification_content_2)
            ),
            NotificationContent(
                context.getString(R.string.notification_title_3),
                context.getString(R.string.notification_content_3, properties.daysForClosed())
            ),
            NotificationContent(
                context.getString(R.string.notification_title_4),
                context.getString(R.string.notification_content_4, properties.daysForClosed())
            ),
            NotificationContent(
                context.getString(R.string.notification_title_5),
                if (properties.daysMissing()!! >= 0) context.getString(R.string.notification_content_5_1)
                else context.getString(R.string.notification_content_5_2)
            ),
            NotificationContent(
                context.getString(R.string.notification_title_6),
                if (properties.daysMissing()!! >= 0) context.getString(R.string.notification_content_6_1)
                else context.getString(R.string.notification_content_6_2)
            ),
            NotificationContent(
                context.getString(R.string.notification_title_7),
                if (properties.daysMissing()!! >= 0) context.getString(R.string.notification_content_7_1)
                else context.getString(
                    R.string.notification_content_7_2,
                    properties.daysMissing()!!.absoluteValue
                )
            ),
            NotificationContent(
                context.getString(R.string.notification_title_8),
                if (properties.daysMissing()!! >= 0) context.getString(R.string.notification_content_8_1)
                else context.getString(
                    R.string.notification_content_8_2,
                    properties.daysMissing()!!.absoluteValue
                )
            )
        )
    }

    private fun createIntent(context: Context) = TaskStackBuilder.create(context)
        .addNextIntent(Intent(context, MainActivity::class.java))
        .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

    private fun builderNotification(
        context: Context,
        pendingIntent: PendingIntent,
        notificationContent: NotificationContent
    ): NotificationCompat.Builder {

        val bigTextNotification = NotificationCompat.BigTextStyle()
        bigTextNotification.bigText(notificationContent.content)
        bigTextNotification.setBigContentTitle(notificationContent.title)

        return NotificationCompat.Builder(context, NotificationChannel.id)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setStyle(bigTextNotification)
            .setColor(Color.parseColor("#ff4655"))
            .setPriority(NotificationChannel.importance)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }

    inner class NotificationContent(val title: String, val content: String)
}