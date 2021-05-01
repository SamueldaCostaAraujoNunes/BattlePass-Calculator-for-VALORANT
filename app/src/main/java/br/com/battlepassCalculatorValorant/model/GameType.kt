package br.com.battlepassCalculatorValorant.model.GameType

open class GameType {
    open var xp = 0
    open var duration = 0F
}

class SemClassificacao : GameType() {
    init {
        xp = 4000
        duration = 0.584F
    }
}

class DisputaDeSpike : GameType() {
    init {
        xp = 1000
        duration = 0.167F
    }
}
