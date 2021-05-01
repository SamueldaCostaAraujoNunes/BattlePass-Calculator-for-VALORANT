package br.com.battlepassCalculatorValorant.extensions.bindingAdapters

import android.view.View
import br.com.battlepassCalculatorValorant.ui.views.CardModule
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

fun AdView.load() {
    if (parent is CardModule) {
        (parent as CardModule).visibility = View.GONE
    }
    val adRequest: AdRequest = AdRequest.Builder().build()
    loadAd(adRequest)
    adListener = object : AdListener() {
        override fun onAdLoaded() {
            if (parent is CardModule) {
                (parent as CardModule).visibility = View.VISIBLE
            }
            super.onAdLoaded()
        }
    }
}