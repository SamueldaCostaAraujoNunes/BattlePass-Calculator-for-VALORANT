package br.com.samuelnunes.valorantpassbattle.ui.viewModel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.samuelnunes.valorantpassbattle.ui.view.activity.AdmobInterstitialActivity

class AdmobViewModel : ViewModel() {

    private val _admobInterstitial = MutableLiveData<AdmobInterstitialActivity.AdShowListener>()
    val admobInterstitial: LiveData<AdmobInterstitialActivity.AdShowListener> = _admobInterstitial

    fun showInterstitial(listener: AdmobInterstitialActivity.AdShowListener) {
        _admobInterstitial.value = listener
    }
}

