package com.example.valorantpassbattle.ui.activity

import com.example.valorantpassbattle.ui.adapter.MyListAdapterRewards
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.valorantpassbattle.model.PassBattle.Tier
import com.example.valorantpassbattle.model.PassBattle.PassBattle
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.model.PassBattle.PassBattleFactory


class MainActivity : AppCompatActivity() {

    var arr = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val passBattle = PassBattleFactory(this).getPassBattle()
        crieListaDeRecompensas(passBattle)
    }

    fun crieListaDeRecompensas(passBattle: PassBattle){
        val listOfRewards = arrayListOf<Tier>()

        for (chapter in passBattle.chapters){
            for(tier in chapter.tiers){
                listOfRewards.add(tier)
            }
        }

        val listView = findViewById<ListView>(R.id.activity_main_list_view)
        val adapter = MyListAdapterRewards(this, listOfRewards)
        listView.setAdapter(adapter)
    }
}