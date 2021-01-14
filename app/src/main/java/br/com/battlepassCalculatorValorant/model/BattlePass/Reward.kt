package br.com.battlepassCalculatorValorant.model.BattlePass

import android.content.Context
import br.com.battlepassCalculatorValorant.R
import org.json.JSONObject

class Reward(json: JSONObject) {
    val nome = Name().translate(json.getJSONObject("Nome"))
    val tipo = json.get("Tipo").toString()
    val imagens = arrayListOf<String>()

    companion object {
        fun getTypeTranslated(context: Context, tipo: String): String {
            val dict = mapOf(
                tudo to context.getString(R.string.tudo),
                skin to context.getString(R.string.skin),
                card to context.getString(R.string.card),
                chaveiro to context.getString(R.string.chaveiro),
                spray to context.getString(R.string.spray),
                titulo to context.getString(R.string.titulo),
                radianita to context.getString(R.string.radianita)
            )
            return dict[tipo] ?: error(tipo)
        }

        const val tudo = "Tudo"
        const val skin = "Skin"
        const val card = "Card"
        const val chaveiro = "Chaveiro"
        const val spray = "Spray"
        const val titulo = "Título"
        const val radianita = "Radianitas"
        val types = arrayListOf(tudo, skin, card, chaveiro, spray, titulo, radianita)
    }

    init {
        val jArray = json.getJSONArray("Imagens")
        for (i in 0 until jArray.length()) {
            imagens.add(jArray[i].toString())
        }
    }

    fun getTypeTranslated(context: Context): String {
        val dict = mapOf(
            tudo to context.getString(R.string.tudo),
            skin to context.getString(R.string.skin),
            card to context.getString(R.string.card),
            chaveiro to context.getString(R.string.chaveiro),
            spray to context.getString(R.string.spray),
            titulo to context.getString(R.string.titulo),
            radianita to context.getString(R.string.radianita)
        )
        return dict[tipo] ?: error(tipo)
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