package br.com.battlepassCalculatorValorant.model.PassBattle

import android.icu.text.SimpleDateFormat
import org.json.JSONObject
import java.util.*


class PassBattle(json: JSONObject) {
    val dateInit = Calendar.getInstance()
    val dateFinally = Calendar.getInstance()
    val name = json.get("Nome").toString()
    val expTotal = json.get("EXP total").toString().toInt()
    val expMissaoDiaria = json.get("EXP Missao Diaria").toString().toInt()
    val expMissaoSemanal = json.get("EXP Missao Semanal").toString().toInt()
    val chapters = arrayListOf<Chapter>()
    val tiers = arrayListOf<Tier>()

    init {

        // Create array Capitulos
        val jArray = json.getJSONArray("Capitulos")
        for (i in 0 until jArray.length()) {
            chapters.add(Chapter(jArray.getJSONObject(i)))
        }
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        // Create dateInit
        var date = json.get("Data Inicial").toString()
        dateInit.time = sdf.parse(date)
        // Create dateFinaly
        date = json.get("Data Final").toString()
        dateFinally.time = sdf.parse(date)

        //Create listTiers
        for (chapter in chapters){
            tiers.addAll(chapter.tiers)
        }
    }

    fun getTier(indexTier: Int): Tier? {
        for (tier in tiers) {
            if (tier.index == indexTier) {
                return tier
            }
        }
        return null
    }

    fun getDateInit(): String? {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        return sdf.format(dateInit.time)
    }

    fun getDateFinally(): String? {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        return sdf.format(dateFinally.time)
    }

}