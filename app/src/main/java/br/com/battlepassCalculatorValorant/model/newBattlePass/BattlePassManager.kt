package br.com.battlepassCalculatorValorant.model.newBattlePass

import android.content.Context
import br.com.battlepassCalculatorValorant.util.ObjectConverters
import com.google.gson.GsonBuilder
import java.time.LocalDate


private const val PATH_INFOS = "novoPasse.json"


class BattlePassManager(context: Context) {
    private val jsonStr: String =
        context.assets.open(PATH_INFOS).bufferedReader().use { it.readText() }
    val passe: NBattlePass =
        GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, ObjectConverters.LD_DESERIALIZER)
            .create().fromJson(jsonStr, NBattlePass::class.java)

    fun getTier(id: Int): NewReward? {
        return passe.tiers.find { it.id == id }
    }

    fun getTiers(tierMax: Int): List<NewReward> {
        return passe.tiers.filter { it.id <= tierMax }
    }

    fun getChapters(): List<NewReward> = passe.capitulos

    fun expParaCompletarTier(n: Int): Int? {
        return when (n) {
            1 -> {
                0
            }
            in 2..55 -> {
                2000 + (n - 2) * 750
            }
            else -> {
                null
            }
        }
    }

    fun totalExpAteOTier(tierMax: Int): Int {
        var aux = 0
        for (tier in 1..tierMax) {
            aux += expParaCompletarTier(tier)!!
        }
        return aux
    }

}

data class NBattlePass(
    val dateInit: LocalDate,
    val dateFinally: LocalDate,
    val expPrimeiroTermo: Int,
    val expRazao: Int,
    val missaoDiaria: ExpMissao,
    val missaoSemanal: List<ExpMissao>,
    val tiers: List<NewReward>,
    val capitulos: List<NewReward>
)

data class ExpMissao(
    val id: Int,
    val exp: Int
)

data class NewReward(
    val id: Int,
    val nome: String,
    val tipo: String,
    val imagens: List<String>
)