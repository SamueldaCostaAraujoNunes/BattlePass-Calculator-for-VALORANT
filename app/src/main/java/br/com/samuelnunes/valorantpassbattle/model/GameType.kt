package br.com.samuelnunes.valorantpassbattle.model.GameType

abstract class GameType {
    abstract var xp: Int
    abstract var duration: Float
}

class SemClassificacao : GameType() {
    override var xp: Int = 4000
    override var duration: Float = 0.584F
}

class DisputaDeSpike : GameType() {
    override var xp: Int = 1000
    override var duration: Float = 0.167F
}

class MataMata : GameType() {
    override var xp: Int = 900
    override var duration: Float = 0.15F
}

class Disparada : GameType() {
    override var xp: Int = 800
    override var duration: Float = 0.167F
}

class Replicacao : GameType() {
    override var xp: Int = 1700
    override var duration: Float = 0.25F
}

class BatalhaNevada : GameType() {
    override var xp: Int = 825
    override var duration: Float = 0.1F
}

