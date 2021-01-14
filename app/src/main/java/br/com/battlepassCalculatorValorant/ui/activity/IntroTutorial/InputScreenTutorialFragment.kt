package br.com.battlepassCalculatorValorant.ui.activity.IntroTutorial

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.BuildConfig
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Historic.UserInputsTier
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.model.Util.ValidateInputUser
import br.com.battlepassCalculatorValorant.ui.Advertisement.Advertisement
import br.com.battlepassCalculatorValorant.ui.notification.NotificationReceiver
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.fragment_input_screen_tutorial.*

class InputScreenTutorialFragment : Fragment() {
    private lateinit var mInterstitialAd: InterstitialAd
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdMob()
    }

    private fun initAdMob() {
        mInterstitialAd = Advertisement(requireContext()).createInterstitial()
    }

    private fun launcherAdMob() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        }
    }

    private fun buttonSaveClick() {
        val validador = ValidateInputUser(
            requireContext(),
            tierinput_dialog_et_level_current,
            tierinput_dialog_et_exp_missing
        )
        if (validador.isValide()) {
            createInputUser()
            launcherAdMob()
            createNotification()
            mostrarProxActivity()
        }
    }

    private fun createInputUser() {
        val tier = tierinput_dialog_et_level_current.text.toString().toInt()
        val expMissing = tierinput_dialog_et_exp_missing.text.toString().toInt()
        val inputUser = UserInputsTier(tier, expMissing)
        val historic = Properties(requireContext()).historic
        historic.create(inputUser)
    }

    private fun mostrarProxActivity() {
        (activity as IntroTutorialActivity?)?.nextActivity()
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