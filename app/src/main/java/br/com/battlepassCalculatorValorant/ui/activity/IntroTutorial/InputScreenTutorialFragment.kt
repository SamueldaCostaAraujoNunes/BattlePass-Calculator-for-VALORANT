package br.com.battlepassCalculatorValorant.ui.activity.IntroTutorial

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.BuildConfig
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.DataBase.SharedPreferences.FirstInputSharedPreferences
import br.com.battlepassCalculatorValorant.model.Historic.Historic
import br.com.battlepassCalculatorValorant.model.Historic.UserInputsTier
import br.com.battlepassCalculatorValorant.model.PassBattle.PassBattleFactory
import br.com.battlepassCalculatorValorant.ui.notification.NotificationReceiver
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.fragment_input_screen_tutorial.*

class InputScreenTutorialFragment : Fragment() {
    private lateinit var mInterstitialAd: InterstitialAd
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdMob()
    }

    private fun initAdMob() {
        mInterstitialAd = InterstitialAd(requireContext())
        mInterstitialAd.adUnitId = resources.getString(R.string.admob_fullscreen_ad)
        mInterstitialAd.loadAd(AdRequest.Builder().build())
    }

    private fun launcherAdMob() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.")
        }
    }

    private fun buttonSaveClick() {
        if (validadeTierIndex()) {
            if (validadeTierExpMissing()) {
                val tier = tierinput_dialog_et_level_current.text.toString().toInt()
                val expMissing = tierinput_dialog_et_exp_missing.text.toString().toInt()
                val inputUser = UserInputsTier(tier, expMissing)
                val historic = Historic(requireContext())
                historic.create(inputUser)
                createNotification()
                launcherAdMob()
                mostrarProxActivity()
            }
        }
    }

    private fun mostrarProxActivity() {
        FirstInputSharedPreferences(requireContext()).firstInputExists = true
        (activity as IntroTutorialActivity?)?.nextActivity()
    }

    fun validadeTierIndex(): Boolean {
        var error: String? = null
        val tv = tierinput_dialog_et_level_current
        val tierStr = tv.text.toString()
        if (tierStr == "") error = "Insira um tier!"
        if (tierStr.isNotEmpty()) {
            if (tierStr.length <= 3) {
                val tierInt = tierStr.toInt()
                if ((tierInt < 0) or (tierInt > 50)) error =
                    "Insira um tier entre 0 e 50!"
            } else {
                error = "Insira um tier menor que 50!"
            }
        }
        tv.error = error
        return error == null
    }

    fun validadeTierExpMissing(): Boolean {
        var error: String? = null
        val tv = tierinput_dialog_et_exp_missing
        val tierExpMissing = tv.text.toString()
        if (tierExpMissing == "") error = "Insira o EXP faltando!"

        val tier = tierinput_dialog_et_level_current
            .text
            .toString()
            .toInt()

        val xpMaxMissig = if ((0 < tier) and (tier <= 50)) PassBattleFactory(requireContext())
            .getPassBattle()
            .getTier(tier)?.expMissing!! else 50000

        if (tierExpMissing.isNotEmpty()) {
            if (tierExpMissing.length <= 5) {
                val expMissing = tierExpMissing.toInt()
                if ((expMissing < 0) or (expMissing > xpMaxMissig)) error =
                    "Insira um EXP entre 0 e ${xpMaxMissig}!"
            } else {
                error = "Insira um EXP menor ou igual a ${xpMaxMissig}!"
            }
        }
        tv.error = error
        return error == null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_input_screen_tutorial, container, false)
        val btnSave = view.findViewById<Button>(R.id.bt_save)
        btnSave.setOnClickListener { buttonSaveClick() }
        return view
    }

    fun createNotification() {
        val intent = Intent(requireContext(), NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
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