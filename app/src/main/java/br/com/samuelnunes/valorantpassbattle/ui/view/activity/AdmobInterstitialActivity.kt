package br.com.samuelnunes.valorantpassbattle.ui.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.activity.AdmobViewModel
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import timber.log.Timber

open class AdmobInterstitialActivity : AppCompatActivity() {
    private val admobViewModel by viewModels<AdmobViewModel>()

    private var mInterstitialAd: InterstitialAd? = null
    private var mAdIsLoading: Boolean = false

    abstract class AdShowListener {
        abstract fun onSucess()
        abstract fun onFailed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)
        loadAd()
        admobViewModel.admobInterstitial.observe(this, { showInterstitial(it) })
    }

    private fun showInterstitial(listener: AdShowListener) {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Timber.d("Ad was dismissed.")
                    mInterstitialAd = null
                    loadAd()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                    Timber.e("Ad failed to show.")
                    mInterstitialAd = null
                    listener.onFailed()
                }

                override fun onAdShowedFullScreenContent() {
                    Timber.d("Ad showed fullscreen content.")
                    listener.onSucess()
                }
            }
            mInterstitialAd?.show(this)
        } else {
            listener.onFailed()
            loadAd()
        }
    }

    private fun loadAd() {
        if (!mAdIsLoading && mInterstitialAd == null) {
            mAdIsLoading = true
            val adRequest = AdRequest.Builder().build()

            InterstitialAd.load(
                this,
                getString(R.string.admob_fullscreen), adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        mInterstitialAd = null
                        mAdIsLoading = false
                        val error = "domain: ${adError.domain}, code: ${adError.code}, " +
                                "message: ${adError.message}"
                        Timber.e(error)
                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        mInterstitialAd = interstitialAd
                        mAdIsLoading = false
                    }
                }
            )
        }
    }

}
