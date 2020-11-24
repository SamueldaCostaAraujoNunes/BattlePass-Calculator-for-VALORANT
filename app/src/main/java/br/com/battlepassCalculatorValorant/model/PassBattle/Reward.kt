package br.com.battlepassCalculatorValorant.model.PassBattle

import org.json.JSONObject

class Reward(json: JSONObject) {
    val nome = json.get("Nome").toString()
    val tipo = json.get("Tipo").toString()
    val imagens = arrayListOf<String>()

    init {
        val jArray = json.getJSONArray("Imagens")
        for (i in 0 until jArray.length()) {
            imagens.add(jArray[i].toString())
        }
    }

    fun isSkin(): Boolean {
        return tipo == "Skin"
    }

    fun isCard(): Boolean {
        return tipo == "Card"
    }

    fun isChaveiro(): Boolean {
        return tipo == "Chaveiro"
    }

    fun isSpray(): Boolean {
        return tipo == "Spray"
    }

    fun isTitulo(): Boolean {
        return tipo == "Título"
    }

    fun isRadianita(): Boolean {
        return tipo == "Radianitas"
    }
}