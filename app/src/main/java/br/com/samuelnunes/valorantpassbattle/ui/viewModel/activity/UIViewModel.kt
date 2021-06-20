package br.com.samuelnunes.valorantpassbattle.ui.viewModel.activity

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.samuelnunes.valorantpassbattle.KEY_THEME
import br.com.samuelnunes.valorantpassbattle.database.dataStore.SettingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UIViewModel @Inject constructor(private val settingsDataStore: SettingsDataStore) :
    ViewModel() {

    private val mensagemSnackbar: MutableLiveData<String> = MutableLiveData<String>()
    val mostrarMensagemNaSnackbar: LiveData<String> = mensagemSnackbar

    private val _onHideBottomNav = MutableLiveData(true)
    val onHideBottomNav: LiveData<Boolean> = _onHideBottomNav

    var themeCurrent: Int =
        settingsDataStore.getInt(KEY_THEME, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

    val nextTheme = fun(value: Int) {
        settingsDataStore.putInt(KEY_THEME, value)
        themeCurrent = value
        AppCompatDelegate.setDefaultNightMode(value)
    }

    fun hideBottomNav() {
        _onHideBottomNav.value = false
    }

    fun showBottomNav() {
        _onHideBottomNav.value = true
    }

    fun sendNewMensageSnackbar(mensage: String) {
        mensagemSnackbar.value = mensage
    }

}