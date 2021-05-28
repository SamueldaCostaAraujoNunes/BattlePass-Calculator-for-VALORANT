package br.com.samuelnunes.valorantpassbattle.notification

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.annotation.CallSuper
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.repository.CalculatorRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

abstract class HiltBroadcastReceiver : BroadcastReceiver() {
    @CallSuper
    override fun onReceive(context: Context, intent: Intent) {
    }
}

@AndroidEntryPoint
class NotificationReceiver : HiltBroadcastReceiver() {

    @Inject
    lateinit var calculador: CalculatorRepository

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        val listNotificationContent = createListNotificationContents(context)
        val pendingIntent: PendingIntent = createIntent(context)
        val builder = builderNotification(context, pendingIntent, listNotificationContent.random())
        NotificationManagerCompat.from(context).notify(0, builder.build())
    }

    private fun createListNotificationContents(context: Context): List<NotificationContent> {
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
                context.getString(R.string.notification_content_3, calculador.daysLeftUntilTheEnd)
            ),
            NotificationContent(
                context.getString(R.string.notification_title_4),
                context.getString(R.string.notification_content_4, calculador.daysLeftUntilTheEnd)
            )
        )
    }

    private fun createIntent(context: Context) = NavDeepLinkBuilder(context)
        .setGraph(R.navigation.nav_graph)
        .setDestination(R.id.homeFragment)
        .createPendingIntent()

    private fun builderNotification(
        context: Context,
        pendingIntent: PendingIntent,
        notificationContent: NotificationContent
    ): NotificationCompat.Builder {
        val colorSecondary = Color.parseColor("#ff4655")
        return NotificationCompat.Builder(context, NotificationChannel.id)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle(notificationContent.title)
            .setStyle(NotificationCompat.BigTextStyle().bigText(notificationContent.content))
            .setColor(colorSecondary)
            .setPriority(NotificationChannel.importance)
            .setContentIntent(pendingIntent)
            .setLights(colorSecondary, 500, 500)
            .setAutoCancel(true)
    }

    inner class NotificationContent(val title: String, val content: String)
}