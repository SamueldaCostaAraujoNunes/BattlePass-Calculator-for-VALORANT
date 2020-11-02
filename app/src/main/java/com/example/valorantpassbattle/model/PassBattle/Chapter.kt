package com.example.valorantpassbattle.model.PassBattle

import org.json.JSONObject

class Chapter(json: JSONObject) {
    val index = json.get("Indice").toString().toInt()
    val reward = json.get("Recompensa").toString()
    val tiers = arrayListOf<Tier>()
    init {
        val jArray = json.getJSONArray("Tiers")
        for (i in 0 until jArray.length()) {
            tiers.add(Tier(jArray.getJSONObject(i)))
        }
    }


}