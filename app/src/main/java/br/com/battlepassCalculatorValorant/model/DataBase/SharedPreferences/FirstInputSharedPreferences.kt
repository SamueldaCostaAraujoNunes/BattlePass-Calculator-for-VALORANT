package br.com.battlepassCalculatorValorant.model.DataBase.SharedPreferences

import android.content.Context

class FirstInputSharedPreferences(context: Context) : MySharedPreferences(context) {
    private val keyFirstInputComplete = "keyFirstInputComplete"

    var firstInputExists: Boolean = get(keyFirstInputComplete, false)
        set(value) = set(keyFirstInputComplete, value)
}