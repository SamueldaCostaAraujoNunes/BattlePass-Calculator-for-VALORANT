package br.com.battlepassCalculatorValorant.model.battlePass

import android.content.Context
import br.com.battlepassCalculatorValorant.R
import org.json.JSONObject

class Reward(json: JSONObject, var position: Int, val fonte: Char) {
    var nome = Name().translate(json.getJSONObject("Nome"))
    var tipo = json.get("Tipo").toString()
    val imagens = arrayListOf<String>()


    companion object {
        fun getTypeTranslated(context: Context, tipo: String): String {
            val dict = mapOf(
                TUDO to context.getString(R.string.tudo),
                SKIN to context.getString(R.string.skin),
                CARD to context.getString(R.string.card),
                CHAVEIRO to context.getString(R.string.chaveiro),
                SPRAY to context.getString(R.string.spray),
                TITULO to context.getString(R.string.titulo),
                RADIANITA to context.getString(R.string.radianita)
            )
            return dict[tipo] ?: error(tipo)
        }

        fun getFonteTranslated(context: Context, id: Char): String? {
            return when (id) {
                CAPITULO -> context.getString(R.string.capitulo)
                EPILOGO -> context.getString(R.string.epilogo)
                TIER -> context.getString(R.string.tier)
                else -> null
            }
        }

        const val CAPITULO = 'C'
        const val EPILOGO = 'E'
        const val TIER = 'T'

        const val TUDO = "Tudo"
        const val SKIN = "Skin"
        const val CARD = "Card"
        const val CHAVEIRO = "Chaveiro"
        const val SPRAY = "Spray"
        const val TITULO = "Título"
        const val RADIANITA = "Radianitas"
        val types = arrayListOf(TUDO, SKIN, CARD, CHAVEIRO, SPRAY, TITULO, RADIANITA)
    }

    init {
        val jArray = json.getJSONArray("Imagens")
        for (i in 0 until jArray.length()) {
            imagens.add(jArray[i].toString())
        }
    }

    fun getTypeTranslated(context: Context): String {
        val dict = mapOf(
            TUDO to context.getString(R.string.tudo),
            SKIN to context.getString(R.string.skin),
            CARD to context.getString(R.string.card),
            CHAVEIRO to context.getString(R.string.chaveiro),
            SPRAY to context.getString(R.string.spray),
            TITULO to context.getString(R.string.titulo),
            RADIANITA to context.getString(R.string.radianita)
        )
        return dict[tipo] ?: error(tipo)
    }

    fun isSkin(): Boolean {
        return tipo == SKIN
    }

    fun isCard(): Boolean {
        return tipo == CARD
    }

    fun isChaveiro(): Boolean {
        return tipo == CHAVEIRO
    }

    fun isSpray(): Boolean {
        return tipo == SPRAY
    }

    fun isTitulo(): Boolean {
        return tipo == TITULO
    }

    fun isRadianita(): Boolean {
        return tipo == RADIANITA
    }
}