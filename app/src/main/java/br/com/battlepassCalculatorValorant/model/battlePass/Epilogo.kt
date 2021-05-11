package br.com.battlepassCalculatorValorant.model.battlePass

import org.json.JSONObject

class Epilogo(
    json: JSONObject,
    private val sizeBattlePass: Int,
    private val expTotalBattlePass: Int
) : Chapter(json) {
    override val fonte = Reward.EPILOGO
    init {
        tiers.map {
            it.expInitial += expTotalBattlePass
            it.index += sizeBattlePass
        }
    }
}
