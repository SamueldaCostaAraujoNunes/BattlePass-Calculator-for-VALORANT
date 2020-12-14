package br.com.battlepassCalculatorValorant.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.ui.Advertisement.Advertisement
import br.com.battlepassCalculatorValorant.ui.dialog.DialogInput
import br.com.battlepassCalculatorValorant.ui.fragment.ChartsFragment
import br.com.battlepassCalculatorValorant.ui.fragment.InfosFragment
import br.com.battlepassCalculatorValorant.ui.fragment.PrincipalFragment
import br.com.battlepassCalculatorValorant.ui.fragment.SettingsFragment
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.activity_main.*


@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {
    private lateinit var mInterstitialAd: InterstitialAd
    private var advertisementCount: Int = 0

    companion object {
        private lateinit var context: Context
        lateinit var mInterstitialAd: InterstitialAd

        fun setContext(con: Context) {
            context = con
            mInterstitialAd = InterstitialAd(context)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContext(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFragment(R.id.fragmentPrincipal, PrincipalFragment())
        createListeners()
        initAdMob()
    }


    private fun initAdMob() {
        advertisementCount = 0
        mInterstitialAd = Advertisement(context).createInterstitial()
    }

    private fun launcherAdMob() {
        if (mInterstitialAd.isLoaded && advertisementCount >= 4) {
            mInterstitialAd.show()
            initAdMob()
        } else {
            advertisementCount++
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
                advertisementCount++
                launcherAdMob()
                val config = nextItem != R.id.item_apps
                bottomNavigationView.transform(fab, config)
                createFragment(R.id.fragmentPrincipal, fragment)
            }
            true
        }
    }
}