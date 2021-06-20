package br.com.samuelnunes.valorantpassbattle.model

import android.content.Context
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.model.dto.BattlePass
import br.com.samuelnunes.valorantpassbattle.model.dto.Reward
import br.com.samuelnunes.valorantpassbattle.util.ObjectConverters
import com.google.gson.GsonBuilder
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class BattlePassManager(context: Context) {
    private val jsonStr: String =
        context.resources.openRawResource(R.raw.passe).bufferedReader().use { it.readText() }

    private val passe: BattlePass =
        GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, ObjectConverters.LD_DESERIALIZER)
            .create().fromJson(jsonStr, BattlePass::class.java)

    private val today: LocalDate = LocalDate.now()
    val dateInit: LocalDate = passe.dateInit
    val dateFinally: LocalDate = passe.dateFinally

    val passDurationInDays: Int = ChronoUnit.DAYS.between(dateInit, dateFinally).toInt()
    val daysFromTheStart: Int = ChronoUnit.DAYS.between(dateInit, today).toInt()
    val daysLeftUntilTheEnd: Int = ChronoUnit.DAYS.between(today, dateFinally).toInt()


    fun getExpMissaoDiaria(days: Int): Int {
        return if (days < passDurationInDays) {
            days * passe.missaoDiaria.exp
        } else {
            passDurationInDays * passe.missaoDiaria.exp
        }
    }

    fun getExpMissaoSemanal(days: Int): Int {
        val semanaAtual = (days / 7)
        val tiers = passe.missaoSemanal.filter { it.id <= semanaAtual }
        val sum = tiers.map { it.exp }.sum()
        return sum
    }

    fun getIdBattlePass(): String {
        return passe.id
    }

    fun getTier(id: Int): Reward? {
        return passe.tiers.find { it.id == id }
    }

    fun getTiers(): List<Reward> = passe.tiers

    fun getChapter(id: Int): Reward? {
        return passe.capitulos.find { it.id == id }
    }

    fun getChapters(): List<Reward> = passe.capitulos

    fun expParaCompletarTier(n: Int): Int {
        return when (n) {
            in 2..50 -> {
                passe.expPrimeiroTermo + (n - 2) * passe.expRazao
            }
            in 51..56 -> passe.expEpilogo
            else -> {
                0
            }
        }
    }

    fun totalExpAteOTier(tierMax: Int): Int {
        var aux = 0
        for (tier in 1..tierMax) {
            aux += expParaCompletarTier(tier)
        }
        return aux
    }

}