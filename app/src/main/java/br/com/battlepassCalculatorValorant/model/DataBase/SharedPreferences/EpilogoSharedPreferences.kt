package br.com.battlepassCalculatorValorant.model.DataBase.SharedPreferences

import android.content.Context
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Singleton.ManagerSharedPreferences

class EpilogoSharedPreferences(context: Context) {
    private val mSharedPreferences: MySharedPreferences =
        ManagerSharedPreferences.getInstance(context)
    private val keyFirstInputComplete = context.getString(R.string.epilogoIsValide)

    var considereOEpilogo: Boolean = mSharedPreferences[keyFirstInputComplete, false]
        set(value) = mSharedPreferences.set(keyFirstInputComplete, value)
}