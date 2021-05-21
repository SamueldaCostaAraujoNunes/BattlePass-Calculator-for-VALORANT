package br.com.battlepassCalculatorValorant.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import br.com.battlepassCalculatorValorant.KEY_DAILY_MISSION
import br.com.battlepassCalculatorValorant.KEY_EPILOGUE
import br.com.battlepassCalculatorValorant.KEY_WEEKLY_MISSION
import br.com.battlepassCalculatorValorant.database.room.AppDB
import br.com.battlepassCalculatorValorant.database.room.model.UserTier
import br.com.battlepassCalculatorValorant.extensions.dateFormat
import br.com.battlepassCalculatorValorant.extensions.daysApart
import br.com.battlepassCalculatorValorant.model.GameType.GameType
import br.com.battlepassCalculatorValorant.model.PrevisoesJogos
import br.com.battlepassCalculatorValorant.model.battlePass.BattlePass
import br.com.battlepassCalculatorValorant.model.battlePass.Chapter
import br.com.battlepassCalculatorValorant.model.battlePass.Tier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.IntStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CalculatorRepository @Inject constructor(
    val battlePass: BattlePass,
    val dataStore: DataStore<Preferences>,
    database: AppDB
) {
    private val userInputHistory = database.userTier
    private val today = Calendar.getInstance()

    val epilogue: Flow<Boolean> = dataStore.data
        .map { pref ->
            pref[KEY_EPILOGUE] ?: false
        }

    val dailyMission: Flow<Boolean> = dataStore.data
        .map { pref ->
            pref[KEY_DAILY_MISSION] ?: false
        }

    val weeklyMission: Flow<Boolean> = dataStore.data
        .map { pref ->
            pref[KEY_WEEKLY_MISSION] ?: false
        }

//    val totalXpBattlePass: Int = battlePass.totalXp

    val totalXpBattlePass: Flow<Int> = epilogue.map {
        battlePass.getExpTotal(it)
    }

    val listTiers: ArrayList<Tier> = battlePass.tiers
    val listChapters: ArrayList<Chapter> = battlePass.chapters

    val passDurationInDays: Int = battlePass.dateFinally.daysApart(battlePass.dateInit) + 1
    val daysFromTheStart: Int = today.daysApart(battlePass.dateInit) + 1
    val daysLeftUntilTheEnd: Int = battlePass.dateFinally.daysApart(today)

    val openingDateOfTheAct: String = battlePass.dateInit.dateFormat()
    val closingDateOfTheAct: String = battlePass.dateFinally.dateFormat()

    val expExpectedPerDay: Flow<ArrayList<Int>> = totalXpBattlePass.map {
        val expListForEachTier = arrayListOf(0)
        val average = it / passDurationInDays
        for (day in IntStream.range(1, passDurationInDays)) {
            expListForEachTier.add(day * average)
        }
        expListForEachTier
    }

    val tiersPerExp: ArrayList<Int> by lazy {
        val progressoPerTier = arrayListOf(0)
        progressoPerTier.addAll(listTiers.map { it.expInitial + it.expMissing })
        progressoPerTier
    }

    val lastUserInput: Flow<UserTier> = userInputHistory.last().map { it ?: UserTier() }
    val allUserInput: Flow<List<UserTier>> =
        userInputHistory.getAll().map { if (it.isEmpty()) listOf(UserTier()) else it }

    val totalExpAlreadyEarned: Flow<Int> = lastUserInput.map { last ->
        val expPass = battlePass.getTier(last.tierCurrent)!!.expInitial
        val expCurrent = battlePass.getTier(last.tierCurrent)!!.expMissing - last.tierExpMissing
        expPass + expCurrent
    }

    val averageExpPerDay: Flow<Int> = totalExpAlreadyEarned.map { xpTotal ->
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
                val tierCurrent = battlePass.getTier(userInput.tierCurrent)!!
                val expCurrent =
                    (tierCurrent.expInitial + (tierCurrent.expMissing - userInput.tierExpMissing)).toFloat()
                val differenceBetweenTiers = tierCurrent.index - lastTier
                val differenceBetweenExp = expCurrent - lastExp
                val average = differenceBetweenExp / differenceBetweenTiers

                for (tier in 1..differenceBetweenTiers) {
                    val xp = ((average * tier) + lastExp).toInt()
                    tiersPerXp.add(xp)
                }
                lastTier = tierCurrent.index
                lastExp = expCurrent
            }
        }
        tiersPerXp
    }

    val tierCurrent: Flow<Tier> = lastUserInput.map { battlePass.getTier(it.tierCurrent)!! }

    val tierIndexCurrent: Flow<Int> = lastUserInput.map { it.tierCurrent }

    val chapterCurrent: Flow<Int> = tierIndexCurrent.map { tierCurrent ->
        (tierCurrent - 1) / 5 + 1
    }

    val totalExpCurrent: Flow<Int> =
        lastUserInput.map { last ->
            val tier = battlePass.getTier(last.tierCurrent)!!
            val xpPass = tier.expInitial
            val xpCurrent = tier.expMissing - last.tierExpMissing
            xpPass + xpCurrent
        }


    val percentageTotal: Flow<Double> =
        totalExpCurrent.combine(totalXpBattlePass) { xpCurrent, xpTotal ->
            xpCurrent.toDouble() * 100 / xpTotal
        }

    val differenceBetweenTheExpectedExpWithTheCurrent: Flow<Int> =
        totalExpAlreadyEarned.combine(totalXpBattlePass) { expCurrent, xpTotal ->
            val expPerDay = (xpTotal.toDouble() / passDurationInDays)
            val expExpected = daysFromTheStart * expPerDay
            expCurrent - expExpected.toInt()
        }


    val finishForecast: Flow<String> =
        totalExpAlreadyEarned.combine(totalXpBattlePass) { expTotal, totalXp ->
            val xpPerDayExpected = (expTotal.toDouble() / daysFromTheStart).toInt()
            val xpDif = totalXp.toDouble() - expTotal.toDouble()
            val totalDays = (xpDif / xpPerDayExpected).toInt() + 1
            val instance = Calendar.getInstance()
            instance.add(Calendar.DAY_OF_MONTH, totalDays)
            instance.dateFormat()
        }

    val daysMissing: Flow<Int> =
        finishForecast.map {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
            val instance = Calendar.getInstance()
            instance.time = sdf.parse(it)!!
            battlePass.dateFinally.daysApart(instance)
        }

    val percentageTier: Flow<Double> =
        lastUserInput.map { tierUser ->
            val tier = battlePass.getTier(tierUser.tierCurrent)!!
            val total = tier.expMissing
            if (total != 0) {
                (total - tierUser.tierExpMissing).toDouble() * 100 / total
            } else {
                100.toDouble()
            }
        }

    fun previsoesJogos(gameType: GameType): Flow<PrevisoesJogos> =
        totalExpAlreadyEarned.combine(totalXpBattlePass) { xpCurrent, xpTotal ->
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


    suspend fun insertUserInput(userTier: UserTier) {
        userInputHistory.insert(userTier)
    }

    suspend fun deleteUserInput(userTier: UserTier) {
        userInputHistory.delete(userTier)
    }

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