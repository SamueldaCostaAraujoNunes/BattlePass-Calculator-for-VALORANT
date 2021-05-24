package br.com.battlepassCalculatorValorant.model.battlePass

data class Reward(
    val id: Int,
    val nome: String,
    val tipo: String,
    val imagens: List<String>
)