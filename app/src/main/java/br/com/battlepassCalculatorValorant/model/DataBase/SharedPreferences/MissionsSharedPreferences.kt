package br.com.battlepassCalculatorValorant.model.DataBase.SharedPreferences

import android.content.Context

class MissionsSharedPreferences(context: Context) : MySharedPreferences(context) {
    private val keyDailyMission = "keyDailyMission"
    private val keyWeeklyMission = "keyWeeklyMission"

    var dailyMission: Boolean = get(keyDailyMission, false)
        set(value) = set(keyDailyMission, value)

    var weeklyMission: Boolean = get(keyWeeklyMission, false)
        set(value) = set(keyWeeklyMission, value)

}
