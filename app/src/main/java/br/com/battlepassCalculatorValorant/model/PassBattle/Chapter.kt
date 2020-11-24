package br.com.battlepassCalculatorValorant.model.PassBattle

import org.json.JSONObject

class Chapter(json: JSONObject) {
    val index = json.get("Indice").toString().toInt()
    val reward = arrayListOf<Reward>()
    val tiers = arrayListOf<Tier>()
    init {
        val tierArray = json.getJSONArray("Tiers")
        for (i in 0 until tierArray.length()) {
            tiers.add(Tier(tierArray.getJSONObject(i)))
        }

        val rewardArray = json.getJSONArray("Recompensa")
        for (i in 0 until rewardArray.length()) {
            reward.add(Reward(rewardArray.getJSONObject(i)))
        }
    }


}