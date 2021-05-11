package br.com.battlepassCalculatorValorant.model.battlePass

import android.content.Context
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

private const val PATH_INFOS = "battlePass.json"
class BattlePassFactory(val context: Context) {
    private val battlePass = BattlePass(readJson(), context)

    private fun readJson(): JSONObject {
        return try {
            val inputStream: InputStream = context.assets.open(PATH_INFOS)
            val jsonStr: String = inputStream.bufferedReader().use { it.readText() }
            JSONObject(jsonStr)
        } catch (e: IOException) {
            JSONObject()
        }
    }

    fun getBattlePass(): BattlePass {
        return battlePass
    }
}