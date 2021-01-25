package br.com.battlepassCalculatorValorant.ui.dialog

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import br.com.battlepassCalculatorValorant.BuildConfig
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Historic.UserInputsTier
import br.com.battlepassCalculatorValorant.model.Singleton.ManagerHistoric
import br.com.battlepassCalculatorValorant.model.Util.ValidateInputUser
import br.com.battlepassCalculatorValorant.ui.Advertisement.Advertisement
import br.com.battlepassCalculatorValorant.ui.notification.NotificationChannel
import br.com.battlepassCalculatorValorant.ui.notification.NotificationReceiver
import com.google.android.gms.ads.InterstitialAd
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.dialog_tierinput.view.*


@Suppress("CAST_NEVER_SUCCEEDS")
class DialogInput(context: Context) : AlertDialog(context) {
    private var mInterstitialAd: InterstitialAd
    var tvTierIndex: TextInputEditText
    var tvTierExpMissing: TextInputEditText
    var mDialogView: View
    var builder: Builder
    lateinit var dialog: AlertDialog

    init {
        mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_tierinput, null)
        tvTierIndex = mDialogView.tierinput_dialog_et_level_current
        tvTierExpMissing = mDialogView.tierinput_dialog_et_exp_missing

        builder = Builder(context)
            .setView(mDialogView)
            .setTitle(context.getString(R.string.insira_seus_dados))

        mInterstitialAd = Advertisement(context).createInterstitial()
        NotificationChannel(context).create()
    }

    override fun show() {
        dialog = builder.show()
        setOnClickListener()
    }

    fun setOnClickListener() {
        val validador = ValidateInputUser(context, tvTierIndex, tvTierExpMissing)
        mDialogView.tierinput_dialog_btn_save.setOnClickListener {
            if (validador.isValide()) {
                createInputTier()
                createNotification()
                launcherAdMob()
                dialog.dismiss()
            }
        }
        mDialogView.tierinput_dialog_btn_cancel.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun createInputTier() {
        val tier = tvTierIndex.text.toString().toInt()
        val expMissing = tvTierExpMissing.text.toString().toInt()
        val inputUser = UserInputsTier(tier, expMissing)
        ManagerHistoric.getInstance(context).create(inputUser)
    }

    fun createNotification() {
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
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

    fun launcherAdMob() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        }
    }
}