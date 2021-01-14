package br.com.battlepassCalculatorValorant.model.BattlePass

import org.json.JSONObject

class Epilogo(
    json: JSONObject,
    private val sizePassBattle: Int,
    private val expTotalPassBattle: Int
) : Chapter(json) {
    init {
        tiers.map {
            it.expInitial += expTotalPassBattle
            it.index += sizePassBattle
        }
    }
}
