package br.com.samuelnunes.valorantpassbattle.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import br.com.samuelnunes.valorantpassbattle.KEY_DAILY_MISSION
import br.com.samuelnunes.valorantpassbattle.KEY_EPILOGUE
import br.com.samuelnunes.valorantpassbattle.KEY_WEEKLY_MISSION
import br.com.samuelnunes.valorantpassbattle.database.room.AppDB
import br.com.samuelnunes.valorantpassbattle.model.BattlePassManager
import br.com.samuelnunes.valorantpassbattle.model.GameType.GameType
import br.com.samuelnunes.valorantpassbattle.model.dto.PrevisoesJogos
import br.com.samuelnunes.valorantpassbattle.model.dto.Reward
import br.com.samuelnunes.valorantpassbattle.model.dto.UserTier
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

    private val totalXpBattlePass: Flow<Int> = epilogue.map { epilogue ->
        val tierMax = if (epilogue) 55 else 50
        battlePassManager.totalExpAteOTier(tierMax)
    }

    val expCurrent: Flow<Int> =
        lastUserInput.map { last ->
            battlePassManager.totalExpAteOTier(last.tierCurrent) - last.tierExpMissing
        }
    private val expBattlePassWithoutMission: Flow<Int> =
        combine(totalXpBattlePass, dailyMission, weeklyMission) { expBattlePass, daily, weekly ->
            val d = getSomaExpMissaoDiariaAteODia(daily, passDurationInDays)
            val w = getSomaExpMissaoSemanalAteODia(weekly, passDurationInDays)
            val i = expBattlePass - d - w
            i
        }

    private val expCurrentWithoutMission: Flow<Int> =
        combine(expCurrent, dailyMission, weeklyMission) { expCurrent, daily, weekly ->
            val d = getSomaExpMissaoDiariaAteODia(daily, daysFromTheStart)
            val w = getSomaExpMissaoSemanalAteODia(weekly, daysFromTheStart)
            val i = expCurrent - d - w
            i
        }

    fun getRewardTiersById(id: Int): Reward? {
        return battlePassManager.getTier(id)
    }

    fun getRewardChapterById(id: Int): Reward? {
        return battlePassManager.getChapter(id)
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


    val averageExpPerDay: Flow<Int> = expCurrent.map { xpTotal ->
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
        expCurrent.combine(totalXpBattlePass) { xpCurrent, xpTotal ->
            xpCurrent.toDouble() * 100 / xpTotal
        }

//    val differenceBetweenTheExpectedExpWithTheCurrent: Flow<Int> =
//        combine(expCurrentWithoutMission, totalXpBattlePass) { expCurrent, xpTotal ->
//            val expPerDay = (xpTotal.toDouble() / passDurationInDays)
//            val expExpected = daysFromTheStart * expPerDay
//            val result = expCurrent - expExpected.toInt()
//            result
//        }

    val expectedEndDay: Flow<LocalDate> =
        combine(
            expCurrent,
            totalXpBattlePass,
            dailyMission,
            weeklyMission
        ) { expTotalUsuario, expTotalPasse, daily, weekly ->
            if (expTotalUsuario > minimoDeExpMissaoParaEsteDia(daily, weekly)) {
                val mediaDeExpPorDia = mediaDeExpPorDia(daily, weekly, expTotalUsuario)
                val diasAteCompletar =
                    diasAteCompletarPasse(mediaDeExpPorDia, expTotalPasse, daily, weekly)
                LocalDate.now().plusDays(diasAteCompletar.toLong())
            } else {
                battlePassManager.dateFinally
            }
        }

    private fun diasAteCompletarPasse(
        mediaDeExpPorDia: Int,
        expTotalPasse: Int,
        daily: Boolean,
        weekly: Boolean
    ): Int {
        var totalExpUntilTheDay = 0
        var day = daysFromTheStart
        while (totalExpUntilTheDay < expTotalPasse) {
            val expWithoutMission = mediaDeExpPorDia * day
            val dailyMission = getSomaExpMissaoDiariaAteODia(daily, day)
            val weeklyMission = getSomaExpMissaoSemanalAteODia(weekly, day)
            totalExpUntilTheDay = expWithoutMission + dailyMission + weeklyMission
            day++
        }
        return day - daysFromTheStart - 1
    }

    private fun minimoDeExpMissaoParaEsteDia(daily: Boolean, weekly: Boolean): Int {
        val d = getSomaExpMissaoDiariaAteODia(daily, daysFromTheStart)
        val w = getSomaExpMissaoSemanalAteODia(weekly, daysFromTheStart)
        return d + w
    }

    private val minimoDeExpEsperadoParaHoje: Flow<Int> = combine(
        expBattlePassWithoutMission,
        dailyMission,
        weeklyMission
    ) { totalXpBattlePass, daily, weekly ->
        val expPerDay = (totalXpBattlePass / passDurationInDays)
        val minimoParaEsteDia = minimoDeExpMissaoParaEsteDia(daily, weekly)
        (expPerDay * daysFromTheStart) + minimoParaEsteDia
    }

    val differenceBetweenTheExpectedExpWithTheCurrent: Flow<Int> = combine(
        minimoDeExpEsperadoParaHoje,
        expCurrent
    ) { minimoDeExpEsperadoParaHoje, expCurrent ->
        expCurrent - minimoDeExpEsperadoParaHoje
    }

    private fun mediaDeExpPorDia(daily: Boolean, weekly: Boolean, xpCurrent: Int): Int {
        val d = getSomaExpMissaoDiariaAteODia(daily, daysFromTheStart)
        val w = getSomaExpMissaoSemanalAteODia(weekly, daysFromTheStart)
        return ((xpCurrent - d - w).toDouble() / daysFromTheStart).toInt()
    }

    private fun getSomaExpMissaoSemanalAteODia(weekly: Boolean, days: Int) =
        if (weekly) battlePassManager.getExpMissaoSemanal(days) else 0

    private fun getSomaExpMissaoDiariaAteODia(daily: Boolean, days: Int) =
        if (daily) battlePassManager.getExpMissaoDiaria(days) else 0

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
        combine(expCurrentWithoutMission, expBattlePassWithoutMission) { xpCurrent, xpTotal ->

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


