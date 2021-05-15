package br.com.battlepassCalculatorValorant.model.Singleton

import android.content.Context
import br.com.battlepassCalculatorValorant.database.SharedPreferences.MSharedPreferences

class ManagerSharedPreferences {
    companion object : SingletonHolder<MSharedPreferences, Context>(::MSharedPreferences)
}