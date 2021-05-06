package br.com.battlepassCalculatorValorant.model.battlePass

import android.content.Context
import android.icu.text.SimpleDateFormat
import org.json.JSONObject
import java.util.*


class BattlePass(json: JSONObject, val context: Context) {
    val dateInit = Calendar.getInstance()
    val dateFinally = Calendar.getInstance()

    //    val name = json.get("Nome").toString()
    val name = Name().translate(json.getJSONObject("Nome"))
    val expTotal = json.get("EXP total").toString().toInt()
    val expMissaoDiaria = json.get("EXP Missao Diaria").toString().toInt()
    val expMissaoSemanal = json.get("EXP Missao Semanal").toString().toInt()
    val epilogoExpTotal = json.get("EXP Epilogo").toString().toInt()

    private val _chapters = arrayListOf<Chapter>()
    private val _tiersChapters = arrayListOf<Tier>()
    private val _epilog = arrayListOf<Epilogo>()
    private val _tiersEpilog = arrayListOf<Tier>()

    var espilogoIsValide: Boolean = false
//        get() = EpilogoSharedPreferences(context).considereOEpilogo
//        set(value) {
//            EpilogoSharedPreferences(context).considereOEpilogo = value
//        }

    val chapters: ArrayList<Chapter>
        get() = if (espilogoIsValide) concatenate(
            _chapters,
            _epilog
        ) else _chapters
    val tiers: ArrayList<Tier>
        get() = if (espilogoIsValide) concatenate(_tiersChapters, _tiersEpilog) else _tiersChapters

    val totalXp: Int
        get() = if (espilogoIsValide)
            expTotal + epilogoExpTotal
        else
            expTotal

    init {
        // Create array Capitulos
        val capitulos = json.getJSONArray("Capitulos")
        for (i in 0 until capitulos.length()) {
            _chapters.add(Chapter(capitulos.getJSONObject(i)))
        }
        //Create _tiersChapters
        for (chapter in _chapters) {
            _tiersChapters.addAll(chapter.tiers)
        }
        // Create array Epilogo
        val epilogo = json.getJSONArray("Epilogo")
        for (i in 0 until epilogo.length()) {
            _epilog.add(Epilogo(epilogo.getJSONObject(i), _tiersChapters.size, expTotal))
        }
        //Create _tiersEpilog
        for (tier in _epilog) {
            _tiersEpilog.addAll(tier.tiers)
        }
        // Create Format Date
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        // Create dateInit
        var date = json.get("Data Inicial").toString()
        dateInit.time = sdf.parse(date)
        // Create dateFinaly
        date = json.get("Data Final").toString()
        dateFinally.time = sdf.parse(date)

    }

    fun getTier(indexTier: Int): Tier? {
        for (tier in tiers) {
            if (tier.index == indexTier) {
                return tier
            }
        }
        return null
    }


    private fun <T> concatenate(vararg lists: List<T>): ArrayList<T> {
        val result: ArrayList<T> = ArrayList()
        for (list in lists) {
            result += list
        }
        return result
    }
}