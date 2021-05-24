package br.com.battlepassCalculatorValorant.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import br.com.battlepassCalculatorValorant.KEY_DAILY_MISSION
import br.com.battlepassCalculatorValorant.KEY_EPILOGUE
import br.com.battlepassCalculatorValorant.KEY_WEEKLY_MISSION
import br.com.battlepassCalculatorValorant.database.room.AppDB
import br.com.battlepassCalculatorValorant.database.room.model.UserTier
import br.com.battlepassCalculatorValorant.model.GameType.GameType
import br.com.battlepassCalculatorValorant.model.PrevisoesJogos
import br.com.battlepassCalculatorValorant.model.battlePass.BattlePassManager
import br.com.battlepassCalculatorValorant.model.battlePass.Reward
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.stream.IntStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CalculatorRepository @Inject constructor(
    val battlePassManager: BattlePassManager,
    val dataStore: DataStore<Preferences>,
    database: AppDB
) {
    private val userInputHistory = database.userTier

    val lastUserInput: Flow<UserTier> = userInputHistory.last().map { it ?: UserTier() }
    val allUserInput: Flow<List<UserTier>> =
        userInputHistory.getAll().map { if (it.isEmpty()) listOf(UserTier()) else it }

    private val epilogue: Flow<Boolean> = dataStore.data
        .map { pref ->
            pref[KEY_EPILOGUE] ?: false
        }

    private val dailyMission: Flow<Boolean> = dataStore.data
        .map { pref ->
            pref[KEY_DAILY_MISSION] ?: false
        }

    private val weeklyMission: Flow<Boolean> = dataStore.data
        .map { pref ->
            pref[KEY_WEEKLY_MISSION] ?: false
        }

    private val totalXpBattlePass: Flow<Int> =
        combine(epilogue, dailyMission, weeklyMission) { epilogue, daily, weekly ->
            val tierMax = if (epilogue) 55 else 50
            val totalExpAteOTier = battlePassManager.totalExpAteOTier(tierMax)
            val d = if (daily) battlePassManager.expDiariaTotal() else 0
            val w = if (weekly) battlePassManager.expSemanalTotal() else 0

            totalExpAteOTier - d - w
        }

    val totalExpCurrent: Flow<Int> =
        combine(lastUserInput, dailyMission, weeklyMission) { last, daily, weekly ->
            val d = if (daily) battlePassManager.getExpMissaoDiaria() else 0
            val w = if (weekly) battlePassManager.getExpMissaoSemanal() else 0
            battlePassManager.totalExpAteOTier(last.tierCurrent) - last.tierExpMissing - d - w
        }

    fun getRewardById(id: Int): Reward? {
        return battlePassManager.getTier(id)
    }

    val listTiers: List<Reward> = battlePassManager.getTiers()

    val listChapters: List<Reward> = battlePassManager.getChapters()

    val passDurationInDays: Int = battlePassManager.passDurationInDays
    val daysFromTheStart: Int = battlePassManager.daysFromTheStart
    val daysLeftUntilTheEnd: Int = battlePassManager.daysLeftUntilTheEnd

    val openingDateOfTheAct: String = battlePassManager.openingDateOfTheAct
    val closingDateOfTheAct: String = battlePassManager.closingDateOfTheAct

    val expExpectedPerDay: Flow<ArrayList<Int>> = totalXpBattlePass.map {
        val expListForEachTier = arrayListOf(0)
        val average = it / passDurationInDays
        for (day in IntStream.range(1, passDurationInDays)) {
            expListForEachTier.add(day * average)
        }
        expListForEachTier
    }

    fun tiersPerExp(): ArrayList<Int> {
        val progressoPerTier = arrayListOf(0)
        progressoPerTier.addAll(listTiers.map { battlePassManager.totalExpAteOTier(it.id + 1) })
        return progressoPerTier
    }


    val averageExpPerDay: Flow<Int> = totalExpCurrent.map { xpTotal ->
        (xpTotal.toDouble() / daysFromTheStart).toInt()
    }

    val projectionOfProgressPerDay: Flow<List<Int>> = averageExpPerDay.map { average ->
        val expProjectionList = arrayListOf(0)
        for (day in IntStream.range(1, passDurationInDays)) {
            expProjectionList.add(day * average)
        }
        expProjectionList
    }

    val listOfTiersCompletedByTheUser: Flow<List<Int>> = allUserInput.map { allUserInputList ->
        val userInputs = allUserInputList.sortedBy { it.tierCurrent }
        val tiersPerXp = arrayListOf(0)
        var lastTier = 0
        var lastExp = 0F

        if (userInputs.isNotEmpty()) {
            for (userInput in userInputs) {
                val expCurrent =
                    battlePassManager.totalExpAteOTier(userInput.tierCurrent + 1).toFloat()
                val differenceBetweenTiers = userInput.tierCurrent - lastTier
                val differenceBetweenExp = expCurrent - lastExp
                val average = differenceBetweenExp / differenceBetweenTiers

                for (tier in 1..differenceBetweenTiers) {
                    val xp = ((average * tier) + lastExp).toInt()
                    tiersPerXp.add(xp)
                }
                lastTier = userInput.tierCurrent
                lastExp = expCurrent
            }
        }
        tiersPerXp
    }

    val tierCurrent: Flow<Reward> =
        lastUserInput.map { battlePassManager.getTier(it.tierCurrent)!! }


    val percentageTotal: Flow<Double> =
        totalExpCurrent.combine(totalXpBattlePass) { xpCurrent, xpTotal ->
            xpCurrent.toDouble() * 100 / xpTotal
        }

    val differenceBetweenTheExpectedExpWithTheCurrent: Flow<Int> =
        totalExpCurrent.combine(totalXpBattlePass) { expCurrent, xpTotal ->
            val expPerDay = (xpTotal.toDouble() / passDurationInDays)
            val expExpected = daysFromTheStart * expPerDay
            val result = expCurrent - expExpected.toInt()
            result
        }


    val expectedEndDay: Flow<LocalDate> =
        totalExpCurrent.combine(totalXpBattlePass) { expTotalUsuario, expTotalPasse ->
            val mediaDeExpPorDia = (expTotalUsuario.toDouble() / daysFromTheStart).toInt()
            val xpDif = expTotalPasse.toDouble() - expTotalUsuario.toDouble()
            val result = (xpDif / mediaDeExpPorDia).toLong()
            LocalDate.now().plusDays(result)
        }

    val daysMissing: Flow<Long> =
        expectedEndDay.map { endDay ->
            endDay.until(battlePassManager.dateFinally, ChronoUnit.DAYS)
        }

    val percentageTier: Flow<Double> =
        lastUserInput.map { tierUser ->
            val total = battlePassManager.expParaCompletarTier(tierUser.tierCurrent)
            if (total != 0) {
                (total - tierUser.tierExpMissing).toDouble() * 100 / total
            } else {
                100.toDouble()
            }
        }

    fun previsoesJogos(gameType: GameType): Flow<PrevisoesJogos> =
        totalExpCurrent.combine(totalXpBattlePass) { xpCurrent, xpTotal ->
            val xpDif = xpTotal - xpCurrent

            val jogosRestantes = xpDif.toFloat() / gameType.xp
            val tempoRestante = jogosRestantes * gameType.duration
            val horasPorDia = (tempoRestante / daysLeftUntilTheEnd)
            val jogosPorDia = horasPorDia / gameType.duration
            PrevisoesJogos(
                jogosRestantes,
                convertHours(tempoRestante),
                jogosPorDia,
                convertHours(horasPorDia)
            )
        }


    suspend fun insertUserInput(userTier: UserTier) = userInputHistory.insert(userTier)

    suspend fun deleteUserInput(userTier: UserTier) = userInputHistory.delete(userTier)

    fun getUserTierById(userTierId: Int): Flow<UserTier> =
        userInputHistory.getUserTierById(userTierId)

    private fun convertHours(time: Float): String {
        val hours = time.toInt()
        val minutes = ((time % 1) * 60).toInt()
        return "${formatTime(hours)}:${formatTime(minutes)}"
    }

    private fun formatTime(value: Int): String {
        return when {
            value == 0 -> {
                "00"
            }
            value < 10 -> {
                "0$value"
            }
            else -> {
                value.toString()
            }
        }
    }
}


