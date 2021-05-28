package br.com.samuelnunes.valorantpassbattle.model.dto

import java.time.LocalDate

data class BattlePass(
    val dateInit: LocalDate,
    val dateFinally: LocalDate,
    val expPrimeiroTermo: Int,
    val expRazao: Int,
    val missaoDiaria: ExpMissao,
    val missaoSemanal: List<ExpMissao>,
    val tiers: List<Reward>,
    val capitulos: List<Reward>
)