package com.example.valorantpassbattle.ui.activity.Chart

import android.content.Context
import com.example.valorantpassbattle.model.Historic.Historic
import com.example.valorantpassbattle.model.PassBattle.PassBattleFactory
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView

class ChartFactory(context: Context, chartView: AAChartView): Chart(context, chartView) {
    val passBattle = PassBattleFactory(context).getPassBattle()
    val listOfRewards = ArrayList(passBattle.tiers.map { it -> it.expInitial })

    fun getTiersPerXp(): ArrayList<Int> {
        return listOfRewards
    }
}