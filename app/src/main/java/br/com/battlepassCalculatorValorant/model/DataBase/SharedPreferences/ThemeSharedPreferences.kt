package br.com.battlepassCalculatorValorant.model.DataBase.SharedPreferences

import android.content.Context
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.SingletonPassBattle.ManagerSharedPreferences

class ThemeSharedPreferences(context: Context) {

    private val mSharedPreferences: MySharedPreferences =
        ManagerSharedPreferences.getInstance(context)
    private val keyThemeStatus = context.getString(R.string.themeStatus)

    var darkMode = mSharedPreferences[keyThemeStatus, 2]
        set(value) = mSharedPreferences.set(keyThemeStatus, value)
}
