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
                "Ei Psiu!! Hora de atualizar seu passe, hein?!",
                "Atualize os dados do passe para se manter informado!"
            ),
            NotificationContent(
                "Já atualizou seu Passe de Batalha hoje?",
                "Atualize suas informações para ter maior dominio sobre o seu passe!"
            ),
            NotificationContent(
                "Já está na hora de atualizar seu passe, hein?!",
                "Faltam apenas ${properties.daysForClosed()} dias para o passe acabar!"
            ),
            NotificationContent(
                "Já está na hora de atualizar seu passe, hein?!",
                "Faltam apenas ${properties.daysForClosed()} dias para o passe acabar!"
            ),
            NotificationContent(
                "Você está afim de jogar?!",
                if (properties.daysMissing()!! >= 0) "Uau, você está Adiantado! Mas, sugiro que se mantenha atualizado!"
                else "Vish, você está um pouco atrasado, my Friend! Atualize o passe para recuperar o tempo perdido!"
            ),
            NotificationContent(
                "Que a faca esteja com você!",
                if (properties.daysMissing()!! >= 0) "Adiantado, você está! Continue no caminho da Luz, Jovem Padawan!"
                else "Atrasado, você está! Evite o lado negro do Passe, Jovem Padawan!"
            ),
            NotificationContent(
                "Carpe diem. Aproveitem o dia de hoje, rapazes!",
                if (properties.daysMissing()!! >= 0) "Uau, você está Adiantado! Mas, sugiro que se mantenha atualizado!"
                else "Vish, você está um pouco atrasado! Atualize o passe para recuperar o tempo perdido!"
            ),
            NotificationContent(
                "Nunca odeie seus inimigos, isso afeta seu julgamento.!",
                if (properties.daysMissing()!! >= 0) "Não se estresse com Bombinhos e Torretas no Pistol, Você está Adiantado!"
                else "Vish, você está um pouco atrasado! Atualize o passe para recuperar o tempo perdido!"
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
//            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_stat_name))
            .setStyle(bigTextNotification)
            .setColor(Color.parseColor("#ff4655"))
            .setPriority(NotificationChannel.importance)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }

    inner class NotificationContent(val title: String, val content: String)
}