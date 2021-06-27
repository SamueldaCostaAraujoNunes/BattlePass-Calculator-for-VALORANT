package br.com.samuelnunes.valorantpassbattle.ui.viewModel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AdmobViewModel : ViewModel() {
    private val _admobInterstitial = MutableLiveData<Boolean>()
    val admobInterstitial: LiveData<Boolean> = _admobInterstitial

    fun showInterstitial() {
        _admobInterstitial.value = true
    }

    var onSucess: (() -> Any)? = null
    var onFailed: (() -> Any)? = null
}

