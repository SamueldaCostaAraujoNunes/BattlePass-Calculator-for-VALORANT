package br.com.battlepassCalculatorValorant.model.PassBattle

import org.json.JSONObject

class Reward(json: JSONObject) {
    val nome = json.get("Nome").toString()
    val tipo = json.get("Tipo").toString()
    val imagens = arrayListOf<String>()

    companion object {
        const val skin = "Skin"
        const val card = "Card"
        const val chaveiro = "Chaveiro"
        const val spray = "Spray"
        const val titulo = "Título"
        const val radianita = "Radianitas"
    }

    init {
        val jArray = json.getJSONArray("Imagens")
        for (i in 0 until jArray.length()) {
            imagens.add(jArray[i].toString())
        }
    }

    fun isSkin(): Boolean {
        return tipo == skin
    }

    fun isCard(): Boolean {
        return tipo == card
    }

    fun isChaveiro(): Boolean {
        return tipo == chaveiro
    }

    fun isSpray(): Boolean {
        return tipo == spray
    }

    fun isTitulo(): Boolean {
        return tipo == titulo
    }

    fun isRadianita(): Boolean {
        return tipo == radianita
    }
}