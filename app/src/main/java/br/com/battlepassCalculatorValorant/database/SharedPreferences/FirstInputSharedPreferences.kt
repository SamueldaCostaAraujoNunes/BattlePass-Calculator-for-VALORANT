package br.com.battlepassCalculatorValorant.database.SharedPreferences

import android.content.Context
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Singleton.ManagerSharedPreferences

class FirstInputSharedPreferences(context: Context) {
    private val mSharedPreferences: MySharedPreferences =
        ManagerSharedPreferences.getInstance(context)
    private val keyFirstInputComplete = context.getString(R.string.firstInputComplete)

    var firstInputExists: Boolean = mSharedPreferences[keyFirstInputComplete, false]
        set(value) = mSharedPreferences.set(keyFirstInputComplete, value)
}