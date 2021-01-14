package br.com.battlepassCalculatorValorant.model.theme

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.DataBase.SharedPreferences.ThemeSharedPreferences

class Theme(val context: Context) {
    private val myThemes = arrayListOf(
        AppCompatDelegate.MODE_NIGHT_NO,
        AppCompatDelegate.MODE_NIGHT_YES,
        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    )
    val styles = arrayOf(
        context.getString(R.string.tema_claro),
        context.getString(R.string.tema_escuro),
        context.getString(R.string.tema_padrao_do_sistema)
    )

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