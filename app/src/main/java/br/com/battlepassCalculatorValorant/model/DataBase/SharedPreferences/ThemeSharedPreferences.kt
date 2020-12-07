package br.com.battlepassCalculatorValorant.model.DataBase.SharedPreferences

import android.content.Context

class ThemeSharedPreferences(context: Context) : MySharedPreferences(context) {
    private val keyThemeStatus = "keyThemeStatus"

    var darkMode = get(keyThemeStatus, 2)
        set(value) = set(keyThemeStatus, value)
}