package br.com.battlepassCalculatorValorant.ui.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.ColorFromXml
import br.com.battlepassCalculatorValorant.model.Historic.Historic
import br.com.battlepassCalculatorValorant.model.PassBattle.PassBattle
import br.com.battlepassCalculatorValorant.model.PassBattle.PassBattleFactory
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.ui.dialog.DialogInput
import br.com.battlepassCalculatorValorant.ui.fragment.ChartsFragment
import br.com.battlepassCalculatorValorant.ui.fragment.InfosFragment
import br.com.battlepassCalculatorValorant.ui.fragment.PrincipalFragment
import br.com.battlepassCalculatorValorant.ui.fragment.SettingsFragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*


@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {
    private lateinit var mInterstitialAd: InterstitialAd

    companion object {
        private lateinit var context: Context
        lateinit var mInterstitialAd: InterstitialAd
        lateinit var historic: Historic
        lateinit var passBattle: PassBattle
        lateinit var properties: Properties
        lateinit var colorXML: ColorFromXml

        fun setContext(con: Context) {
            context = con
            historic = Historic(context)
            passBattle = PassBattleFactory(context).getPassBattle()
            properties = Properties(historic, passBattle)
            colorXML = ColorFromXml(context)
            MobileAds.initialize(context, R.string.admob_app_id.toString())
            mInterstitialAd = InterstitialAd(context)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContext(this)
        setContentView(R.layout.activity_main)
        createFragment(R.id.fragmentPrincipal, PrincipalFragment())
        createListeners()
        initAdMob()
    }


    fun initAdMob() {
        mInterstitialAd = InterstitialAd(context)
        mInterstitialAd.adUnitId = context.resources.getString(R.string.admob_fullscreen_ad)
        mInterstitialAd.loadAd(AdRequest.Builder().build())
    }

    fun launcherAdMob() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
            initAdMob()
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.")
        }
    }

    override fun onResume() {
        super.onResume()
        bottomNavigationView.selectedItemId = R.id.item_home
    }

    private fun createFragment(layout: Int, fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction().replace(layout, fragment).commit()
    }

    private fun createListeners() {
        createNavigationItemSelectedListener()
        fab.setOnClickListener { DialogInput(this).show() }
    }

    private fun createNavigationItemSelectedListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val previousItem = bottomNavigationView.selectedItemId
            val nextItem = item.itemId
            if (previousItem != nextItem) {
                val fragment: androidx.fragment.app.Fragment =
                    when (nextItem) {
                        R.id.item_home -> PrincipalFragment()
                        R.id.item_timeline -> ChartsFragment()
                        R.id.item_timer -> InfosFragment()
                        R.id.item_apps -> SettingsFragment()
                        else -> PrincipalFragment()
                    }
                val config = nextItem != R.id.item_apps
                bottomNavigationView.transform(fab, config)
//                if (config) {
//                    launcherAdMob()
//                }
                createFragment(R.id.fragmentPrincipal, fragment)
            }
            true
        }
    }
}