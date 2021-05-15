package br.com.battlepassCalculatorValorant.ui.viewModel.activity

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.battlepassCalculatorValorant.KEY_THEME
import br.com.battlepassCalculatorValorant.database.SharedPreferences.MSharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UIViewModel @Inject constructor(private val mSharedPreferences: MSharedPreferences) :
    ViewModel() {
    private val _onHideBottomNav = MutableLiveData<Boolean>()
    val onHideBottomNav: LiveData<Boolean> = _onHideBottomNav

    var themeCurrent: Int =
        mSharedPreferences[KEY_THEME, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM]


    val nextTheme = fun(value: Int) {
        mSharedPreferences.set(KEY_THEME, value)
        themeCurrent = value
        AppCompatDelegate.setDefaultNightMode(value)
    }

    fun hideBottomNav() {
        _onHideBottomNav.value = false
    }

    fun showBottomNav() {
        _onHideBottomNav.value = true
    }
}