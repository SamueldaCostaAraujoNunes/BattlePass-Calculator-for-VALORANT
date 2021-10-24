package br.com.samuelnunes.valorantpassbattle.model.GameType

import br.com.samuelnunes.valorantpassbattle.R

enum class GameType {

    SEM_CLASS {
        override var xp: Int = 4000
        override var duration: Float = 0.584F
        override var iconRes: Int = R.drawable.ic_normal
        override var textRes: Int = R.string.sem_classificacao
    },

    DISPUTA_DE_SPIKE {
        override var xp: Int = 1000
        override var duration: Float = 0.167F
        override var iconRes: Int = R.drawable.ic_disputadespike
        override var textRes: Int = R.string.disputa_de_spike
    },

    MATA_MATA {
        override var xp: Int = 900
        override var duration: Float = 0.15F
        override var iconRes: Int = R.drawable.ic_matamata
        override var textRes: Int = R.string.mata_mata
    },

    DISPARADA {
        override var xp: Int = 800
        override var duration: Float = 0.167F
        override var iconRes: Int = R.drawable.ic_disparada
        override var textRes: Int = R.string.disparada
    },

    REPLICACAO {
        override var xp: Int = 1700
        override var duration: Float = 0.25F
        override var iconRes: Int = R.drawable.ic_replicacao
        override var textRes: Int = R.string.replicacao
    },

    BATALHA_NEVADA {
        override var xp: Int = 825
        override var duration: Float = 0.1F
        override var iconRes: Int = R.drawable.ic_batalhanevada
        override var textRes: Int = R.string.batalhanevada
    };

    abstract var xp: Int
    abstract var duration: Float
    abstract var iconRes: Int
    abstract var textRes: Int
}