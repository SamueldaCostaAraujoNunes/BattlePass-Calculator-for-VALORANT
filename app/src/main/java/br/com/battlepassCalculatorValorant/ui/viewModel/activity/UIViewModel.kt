package br.com.battlepassCalculatorValorant.ui.viewModel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UIViewModel @Inject constructor() : ViewModel() {
    private val _onHideBottomNav = MutableLiveData<Boolean>()
    val onHideBottomNav: LiveData<Boolean> = _onHideBottomNav

    fun hideBottomNav() {
        _onHideBottomNav.value = false
    }

    fun showBottomNav() {
        _onHideBottomNav.value = true
    }
}