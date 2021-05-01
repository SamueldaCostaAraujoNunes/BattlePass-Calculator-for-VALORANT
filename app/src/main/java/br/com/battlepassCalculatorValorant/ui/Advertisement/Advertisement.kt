package br.com.battlepassCalculatorValorant.ui.Advertisement

import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import br.com.battlepassCalculatorValorant.BuildConfig
import br.com.battlepassCalculatorValorant.R
import com.google.android.gms.ads.*

class Advertisement(val context: Context) {

    companion object {
        val BANNER = AdSize.BANNER
        val FULL_BANNER = AdSize.FULL_BANNER
        val LARGE_BANNER = AdSize.LARGE_BANNER
        val LEADERBOARD = AdSize.LEADERBOARD
        val MEDIUM_RECTANGLE = AdSize.MEDIUM_RECTANGLE
        val WIDE_SKYSCRAPER = AdSize.WIDE_SKYSCRAPER
        val SMART_BANNER = AdSize.SMART_BANNER
        val FLUID = AdSize.FLUID
    }

    fun createBanner(view: RelativeLayout, idAdMob: Int, size: AdSize = SMART_BANNER) {
        val adView = AdView(context)
        val parent = view.parent as View
        parent.visibility = View.GONE
        adView.visibility = View.GONE
        adView.adSize = size

        adView.adUnitId = if (BuildConfig.DEBUG) {
            context.getString(R.string.admob_card_id_test)
        } else {
            context.getString(idAdMob)
        }
        view.addView(adView)
        MobileAds.initialize(context) {}
        val adRequest =
            AdRequest
                .Builder()
                .build()
        adView.loadAd(adRequest)
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                adView.visibility = View.VISIBLE
                parent.visibility = View.VISIBLE
                super.onAdLoaded()
            }
        }
    }


    fun createInterstitial(): InterstitialAd {
        val mInterstitialAd = InterstitialAd(context)
        mInterstitialAd.adUnitId = if (BuildConfig.DEBUG) {
            context.getString(R.string.admob_fullscreen_id_teste)
        } else {
            context.getString(R.string.admob_fullscreen_ad)
        }
        mInterstitialAd.loadAd(
            AdRequest
                .Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build()
        )
        return mInterstitialAd
    }
}