package br.com.battlepassCalculatorValorant.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import br.com.battlepassCalculatorValorant.database.room.AppDB
import br.com.battlepassCalculatorValorant.database.room.model.UserTier
import br.com.battlepassCalculatorValorant.extensions.daysApart
import br.com.battlepassCalculatorValorant.model.GameType.DisputaDeSpike
import br.com.battlepassCalculatorValorant.model.GameType.GameType
import br.com.battlepassCalculatorValorant.model.GameType.SemClassificacao
import br.com.battlepassCalculatorValorant.model.battlePass.BattlePass
import br.com.battlepassCalculatorValorant.model.battlePass.Chapter
import br.com.battlepassCalculatorValorant.model.battlePass.Tier
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.IntStream
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList
import kotlin.math.roundToLong


@Singleton
class CalculatorRepository @Inject constructor(
    private val battlePass: BattlePass,
    database: AppDB
) {
    private val historic = database.userTier

    private val semClassificacao: SemClassificacao = SemClassificacao()
    private val disputaDeSpike: DisputaDeSpike = DisputaDeSpike()

    private val lastUserInput: LiveData<UserTier> =
        historic.last().asLiveData().map { it ?: UserTier() }
    private val allUserInput: LiveData<List<UserTier>> =
        historic.getAll().asLiveData().map { it ?: listOf() }

    val listTiers: ArrayList<Tier> = battlePass.tiers
    val listChapters: ArrayList<Chapter> = battlePass.chapters
    val daysDurationBattlePass: Int = battlePass.dateFinally.daysApart(battlePass.dateInit)

    val dataInicioAto: String = battlePass.getDateInit()
    val dataFinalAto: String = battlePass.getDateFinally()

    val totalXpBattlePass: Int = if (battlePass.espilogoIsValide)
        battlePass.expTotal + battlePass.epilogoExpTotal
    else
        battlePass.expTotal

    val days = Calendar.getInstance().daysApart(battlePass.dateInit)


    val totalExp: LiveData<Int> = lastUserInput.map { last ->
        val xpPass = battlePass.getTier(last.tierCurrent)!!.expInitial
        val xpCurrent = battlePass.getTier(last.tierCurrent)!!.expMissing - last.tierExpMissing
        xpPass + xpCurrent
    }

    val xpPerDay: LiveData<Int> = totalExp.map { xpTotal ->
        (xpTotal.toDouble() / days).toInt()
    }

    fun getExpectedExpPerDay(): ArrayList<Int> {
        val listOfAllExpExpectedPerDay = ArrayList<Int>()
        listOfAllExpExpectedPerDay.add(0)
        val daysDurationBattlePass = daysDurationBattlePass
        val razao = totalXpBattlePass / daysDurationBattlePass
        for (day in IntStream.range(1, daysDurationBattlePass)) {
            listOfAllExpExpectedPerDay.add(day * razao)
        }
        return listOfAllExpExpectedPerDay
    }


    val realProgressPerDay: LiveData<List<Int>> = xpPerDay.map { razao ->
        val listOfAllExpExpectedPerDay = ArrayList<Int>()
        listOfAllExpExpectedPerDay.add(0)
        val daysDurationBattlePass = daysDurationBattlePass
        for (day in IntStream.range(1, daysDurationBattlePass)) {
            listOfAllExpExpectedPerDay.add(day * razao)
        }
        listOfAllExpExpectedPerDay
    }


    fun getTiersPerXp(): ArrayList<Int> {
        val progressoPerTier = ArrayList<Int>()
        progressoPerTier.add(0)
        progressoPerTier.addAll(battlePass.tiers.map { it.expInitial + it.expMissing })
        return progressoPerTier
    }

    val historicTierPositionPerXp: LiveData<List<Int>> = allUserInput.map { it ->
        val mHistoric = it.sortedBy { it.tierCurrent }
        val tiersPerXp = ArrayList<Int>()
        var ultimoTier = 0
        var ultimoXp = 0F

        if (mHistoric.isNotEmpty()) {
            tiersPerXp.add(0)
            for (tierUserInput in mHistoric) {
                val tierCurrent =
                    battlePass.getTier(tierUserInput.tierCurrent) ?: battlePass.getTier(50)!!
                val expCurrent =
                    (tierCurrent.expInitial + (tierCurrent.expMissing - tierUserInput.tierExpMissing)).toFloat()
                val diferencaDeTier = tierCurrent.index - ultimoTier
                val diferencaDeXp = expCurrent - ultimoXp
                val razao = diferencaDeXp / diferencaDeTier

                for (t in 1..diferencaDeTier) {
                    val xp = ((razao * t) + ultimoXp).toInt()
                    tiersPerXp.add(xp)
                }
                ultimoTier = tierCurrent.index
                ultimoXp = expCurrent
            }
        }
        tiersPerXp
    }

    val tierCurrent: LiveData<Tier> = lastUserInput.map { battlePass.getTier(it.tierCurrent)!! }

    val tierIndexCurrent: LiveData<Int> = lastUserInput.map { it.tierCurrent }

    val chapterCurrent: LiveData<Int> = tierIndexCurrent.map { tierCurrent ->
        (tierCurrent - 1) / 5 + 1
    }

    val totalXpCurrent: LiveData<Int> =
        lastUserInput.map { last ->
            val tier = battlePass.getTier(last.tierCurrent)!!
            val xpPass = tier.expInitial
            val xpCurrent = tier.expMissing - last.tierExpMissing
            xpPass + xpCurrent
        }


    val progressPorcent: LiveData<Double> = totalXpCurrent.map {
        val num = (it.toDouble() / totalXpBattlePass) * 100
        (num * 100.0).roundToLong() / 100.0
    }

    val expAdiantoOuAtrasado: LiveData<Int> = totalExp.map {
        val daysDurationBattlePass = daysDurationBattlePass
        val xpPerDay = (totalXpBattlePass.toDouble() / daysDurationBattlePass)
        val xpExpected = days * xpPerDay
        it - xpExpected.toInt()
    }

    @SuppressLint("SimpleDateFormat")
    val finishForecast: LiveData<String> = totalExp.map { xpTotal ->
        val xpPerDayExpected = (xpTotal.toDouble() / days).toInt()
        val xpDif = totalXpBattlePass.toDouble() - xpTotal.toDouble()
        val totalDays = xpDif / xpPerDayExpected
        val dateFinally = Calendar.getInstance()
        dateFinally.add(Calendar.DAY_OF_MONTH, totalDays.toInt() + 1)
        SimpleDateFormat("dd/MM/yyyy").format(dateFinally.time)
    }

    val daysMissing: LiveData<Int> =
        finishForecast.map {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
            val dateFinal = Calendar.getInstance()
            dateFinal.time = sdf.parse(it)!!
            battlePass.dateFinally.daysApart(dateFinal)
        }


    val percentageTier: LiveData<Double> =
        lastUserInput.map { tierUser ->
            val tier = battlePass.getTier(tierUser.tierCurrent)!!
            val total = tier.expMissing
            if (total != 0) {
                (total - tierUser.tierExpMissing).toDouble() * 100 / total
            } else {
                100.toDouble()
            }
        }
    val daysForClosed: Int = battlePass.dateFinally.daysApart(Calendar.getInstance())

    fun jogosRestantes(gameType: GameType): LiveData<Float> =
        totalExp.map {
            val xpTotal = totalXpBattlePass
            val xpDif = xpTotal - it
            xpDif.toFloat() / gameType.xp
        }


    fun tempoRestante(gameType: GameType): LiveData<Float> =
        jogosRestantes(gameType).map { it * gameType.duration }

    fun jogosPorDia(gameType: GameType): LiveData<Int> =
        jogosRestantes(gameType).map { (it / daysDurationBattlePass).toInt() + 1 }


    fun horasPorDia(gameType: GameType): LiveData<Float> = jogosPorDia(gameType).map {
        it * gameType.duration
    }

    val expMissaoDiaria: Float =
        (battlePass.expMissaoDiaria.toFloat() / daysDurationBattlePass) * days


    val expMissaoSemanal: Float =
        (battlePass.expMissaoSemanal.toFloat() / (daysDurationBattlePass / 7) * (days / 7))


    val expNormalGame: LiveData<Float> = totalExp.map {
        it - expMissaoDiaria + expMissaoSemanal
    }


}