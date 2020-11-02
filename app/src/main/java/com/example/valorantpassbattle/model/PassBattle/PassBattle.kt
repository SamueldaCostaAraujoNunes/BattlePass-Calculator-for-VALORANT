package com.example.valorantpassbattle.model.PassBattle

import android.util.Log
import org.json.JSONObject
import java.util.*


class PassBattle(json: JSONObject) {
    val dateInit = Calendar.getInstance()
    val dateFinally = Calendar.getInstance()
    val name = json.get("Nome").toString()
    val expTotal = json.get("EXP total").toString().toInt()
    val chapters = arrayListOf<Chapter>()
    val tiers = arrayListOf<Tier>()

    init {

        // Create array Capitulos
        val jArray = json.getJSONArray("Capitulos")
        for (i in 0 until jArray.length()) {
            chapters.add(Chapter(jArray.getJSONObject(i)))
        }
        // Create dateInit
        var date = json.get("Data Inicial").toString()
        var dateSplit = date.split("/")
        var day = dateSplit[0].toInt()
        var month = dateSplit[1].toInt()
        var year = dateSplit[2].toInt()
        dateInit.clear()
        dateInit.set(year, month, day)
        Log.i("Data Inicial: ", dateInit.toString())

        // Create dateFinaly
        date = json.get("Data Final").toString()
        dateSplit = date.split("/")
        day = dateSplit[0].toInt()
        month = dateSplit[1].toInt()
        year = dateSplit[2].toInt()
        dateFinally.clear()
        dateFinally.set(year, month, day)
        Log.i("Data Final: ", dateFinally.toString())

        //Create listTiers
        for (chapter in chapters){
            tiers.addAll(chapter.tiers)
        }
    }

    fun getTier(indexTier: Int): Tier? {
        for(tier in tiers){
            if(tier.index == indexTier){
                return tier
            }
        }
        return null
    }
}