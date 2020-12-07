package br.com.battlepassCalculatorValorant.ui.theme

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import br.com.battlepassCalculatorValorant.model.DataBase.SharedPreferences.ThemeSharedPreferences

class Theme(val context: Context) {
    private val myThemes = arrayListOf(
        AppCompatDelegate.MODE_NIGHT_NO,
        AppCompatDelegate.MODE_NIGHT_YES,
        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    )
    val styles = arrayOf("Light", "Dark", "System default")

    fun checkTheme() {
        AppCompatDelegate.setDefaultNightMode(myThemes[ThemeSharedPreferences(context).darkMode])
    }

    fun setThemeMode(mode: Int) {
        ThemeSharedPreferences(context).darkMode = mode
        checkTheme()
    }

    fun getThemeMode(): Int {
        return ThemeSharedPreferences(context).darkMode
    }
}