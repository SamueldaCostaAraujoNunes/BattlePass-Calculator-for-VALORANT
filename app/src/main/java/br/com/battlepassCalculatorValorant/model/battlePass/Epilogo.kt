package br.com.battlepassCalculatorValorant.model.battlePass

import org.json.JSONObject

class Epilogo(
    json: JSONObject,
    private val sizePassBattle: Int,
    private val expTotalPassBattle: Int
) : Chapter(json) {
    override val fonte = Reward.EPILOGO
    init {
        tiers.map {
            it.expInitial += expTotalPassBattle
            it.index += sizePassBattle
        }
    }
}
