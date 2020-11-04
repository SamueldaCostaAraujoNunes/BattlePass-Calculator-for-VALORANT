package com.example.valorantpassbattle.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.ui.activity.Charts.Charts
import com.example.valorantpassbattle.model.Historic.Historic
import com.example.valorantpassbattle.model.PassBattle.PassBattle
import com.example.valorantpassbattle.model.PassBattle.PassBattleFactory
import com.example.valorantpassbattle.model.Historic.UserInputsXp
import com.github.aachartmodel.aainfographics.aachartcreator.*

class GraphicPassBattleActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var et_tier: EditText
    lateinit var et_exp_missing: EditText
    lateinit var bt_save: Button
    lateinit var passBattle: PassBattle
    lateinit var listOfRewards: ArrayList<Int>
    lateinit var historic: Historic
    lateinit var aaChartView: AAChartView
    lateinit var chart: Charts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graphic_pass_battle)
        createViews();
        setClickListener();
        createObjects();
    }

    override fun onResume() {
        super.onResume()
        attCharts("XP por Tier", listOfRewards);
    }

    override fun onClick(view: View) {
        when ( view.id) {
            R.id.bt_save -> clickBtSave()
        }
    }

    fun createViews(){
        aaChartView = findViewById<AAChartView>(R.id.aa_chart_view)
        et_tier = findViewById<EditText>(R.id.activity_graphic_pass_battle_et_level_current)
        et_exp_missing = findViewById<EditText>(R.id.activity_graphic_pass_battle_et_exp_missing)
        bt_save = findViewById<Button>(R.id.bt_save)
    }

    fun setClickListener(){
        bt_save.setOnClickListener(this)
    }

    fun createObjects(){
        chart = Charts(aaChartView)
        passBattle = PassBattleFactory(this).getPassBattle()
        historic = Historic()
        listOfRewards = ArrayList(passBattle.tiers.map { it -> it.expInitial })
    }

    private fun clickBtSave() {
        val tierIndex = et_tier.text.toString().toInt()
        val xpFaltando = et_exp_missing.text.toString().toInt()
        val historic = Historic()
        historic.add(UserInputsXp(tierIndex, xpFaltando, passBattle))
        attCharts("XP Media", historic.generateMedianProgress())
    }

    fun attCharts(label: String, data: ArrayList<Int>){
        chart.setData(label, data)
        chart.show()
    }
}

