package br.com.battlepassCalculatorValorant.model.SingletonPassBattle

import android.content.Context
import br.com.battlepassCalculatorValorant.model.DataBase.SharedPreferences.MySharedPreferences

class ManagerSharedPreferences {
    companion object : SingletonHolder<MySharedPreferences, Context>(::MySharedPreferences)
}