package br.com.battlepassCalculatorValorant.model.DataBase.SharedPreferences

import android.content.Context
import br.com.battlepassCalculatorValorant.model.SingletonPassBattle.ManagerSharedPreferences

class FirstInputSharedPreferences(context: Context) {
    private val mSharedPreferences: MySharedPreferences =
        ManagerSharedPreferences.getInstance(context)
    private val keyFirstInputComplete = "keyFirstInputComplete"

    var firstInputExists: Boolean = mSharedPreferences[keyFirstInputComplete, false]
        set(value) = mSharedPreferences.set(keyFirstInputComplete, value)
}