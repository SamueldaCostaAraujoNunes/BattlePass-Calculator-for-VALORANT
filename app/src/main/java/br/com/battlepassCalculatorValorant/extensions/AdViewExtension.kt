package br.com.battlepassCalculatorValorant.extensions.bindingAdapters

import android.view.View
import br.com.battlepassCalculatorValorant.ui.view.viewsCustom.CardModule
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

//fun interstitialAdLoad(activity: Activity): LiveData<Resource<InterstitialAd>> =
//    liveData(Dispatchers.IO) {
//        val adRequest = AdRequest.Builder().build()
//        emit(Resource.loading())
//
//        InterstitialAd.load(
//            activity,
//            activity.getString(R.string.admob_fullscreen_id_teste),
//            adRequest,
//            object : InterstitialAdLoadCallback() {
//                override fun onAdFailedToLoad(adError: LoadAdError) {
//                    GlobalScope.launch(Dispatchers.IO) {
//                        emit(Resource.error<InterstitialAd>(adError.message))
//                    }
//                }
//
//                override fun onAdLoaded(interstitialAd: InterstitialAd) {
//                    GlobalScope.launch(Dispatchers.IO) {
//                        emit(Resource.success(interstitialAd))
//                    }
//                }
//            })
//
//    }

