package br.com.battlepassCalculatorValorant.model.BattlePass

import org.json.JSONObject

class Tier(json: JSONObject) {
    var index = json.get("Indice").toString().toInt()
    var expInitial = json.get("EXP Inicial").toString().toInt()
    val expMissing = json.get("EXP Faltante").toString().toInt()
    val totalPercentage = json.get("Porcentagem").toString().toFloat()
    val reward = arrayListOf<Reward>()

    init {
        val rewardArray = json.getJSONArray("Recompensa")
        for (i in 0 until rewardArray.length()) {
            reward.add(Reward(rewardArray.getJSONObject(i)))
        }
    }
}