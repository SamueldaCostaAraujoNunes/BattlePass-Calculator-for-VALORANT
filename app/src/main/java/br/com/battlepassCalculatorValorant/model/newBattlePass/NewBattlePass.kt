package br.com.battlepassCalculatorValorant.model.newBattlePass

import android.content.Context
import android.icu.text.SimpleDateFormat
import org.json.JSONObject
import java.io.InputStream
import java.util.*

private const val PATH_INFOS = "novoPasse.json"

class NewBattlePass(context: Context) {

    val dateInit: Calendar = Calendar.getInstance()
    val dateFinally: Calendar = Calendar.getInstance()
    var urlPath: String
    var expPrimeiroTermo: Int
    var expRazao: Int
    var missaoDiaria: ExpMissao
    var missaoSemanal = arrayListOf<ExpMissao>()
    private var _tiers = arrayListOf<NewReward>()
    var capitulos = arrayListOf<NewReward>()

    fun getTier(id: Int): NewReward {
        return _tiers[id - 1]
    }

    fun getTiers(tierMax: Int): List<NewReward> {
        return _tiers.filter { it.id <= tierMax }
    }

    init {
        val inputStream: InputStream = context.assets.open(PATH_INFOS)
        val jsonStr: String = inputStream.bufferedReader().use { it.readText() }
        val json = JSONObject(jsonStr)

        // Create Format Date
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        dateInit.time = sdf.parse(json.get("Data Inicial").toString())
        dateFinally.time = sdf.parse(json.get("Data Final").toString())
        urlPath = json.get("UrlPath").toString()
        expPrimeiroTermo = json.get("ExpPrimeiroTermo").toString().toInt()
        expRazao = json.get("ExpRazao").toString().toInt()
        val diaria = json.getJSONObject("MissaoDiaria")
        missaoDiaria =
            ExpMissao(diaria.get("ID").toString().toInt(), diaria.get("EXP").toString().toInt())
        val semanal = json.getJSONArray("MissaoSemanal")
        for (i in 0 until semanal.length()) {
            val missao = semanal.getJSONObject(i)
            missaoSemanal.add(
                ExpMissao(
                    missao.get("ID").toString().toInt(),
                    missao.get("EXP").toString().toInt()
                )
            )
        }
        _tiers = createRewards(json, "Tiers")
        capitulos = createRewards(json, "Capitulos")
    }

    private fun createRewards(json: JSONObject, capStr: String): ArrayList<NewReward> {
        val capitulosJson = json.getJSONArray(capStr)
        val storage = arrayListOf<NewReward>()
        for (i in 0 until capitulosJson.length()) {
            val cap = capitulosJson.getJSONObject(i)
            val imagens = cap.getJSONArray("Imagens")
            val lista = arrayListOf<String>()
            for (j in 0 until imagens.length()) {
                val image = imagens.getJSONObject(j)
                lista.add(urlPath + image.toString())
            }
            storage.add(
                NewReward(
                    cap.get("ID").toString().toInt(),
                    cap.get("Nome").toString(),
                    cap.get("Tipo").toString(),
                    lista
                )
            )
        }
        return storage
    }

}

data class ExpMissao(val id: Int, val exo: Int)
data class NewReward(val id: Int, val nome: String, val tipo: String, val imagens: List<String>)