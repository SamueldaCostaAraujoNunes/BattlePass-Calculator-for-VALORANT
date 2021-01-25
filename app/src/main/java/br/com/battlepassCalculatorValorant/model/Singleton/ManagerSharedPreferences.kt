package br.com.battlepassCalculatorValorant.model.Singleton

import android.content.Context
import br.com.battlepassCalculatorValorant.model.DataBase.SharedPreferences.MySharedPreferences

class ManagerSharedPreferences {
    companion object : SingletonHolder<MySharedPreferences, Context>(::MySharedPreferences)
}