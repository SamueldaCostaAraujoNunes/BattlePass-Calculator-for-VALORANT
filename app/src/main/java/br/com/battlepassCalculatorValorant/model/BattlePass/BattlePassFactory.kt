package br.com.battlepassCalculatorValorant.model.BattlePass

import android.content.Context
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class BattlePassFactory(val context: Context) {
    private val PATH_INFOS = "battlePass.json"
    private val json = readJson()
    private val passBattle = BattlePass(json, context)

    private fun readJson(): JSONObject {
        return try {
            val inputStream: InputStream = context.assets.open(PATH_INFOS)
            val jsonStr: String = inputStream.bufferedReader().use { it.readText() }
            JSONObject(jsonStr)
        } catch (e: IOException) {
            JSONObject()
        }
    }

//    private fun readJson(): JSONObject {
//        return try {
//            val inputStream: InputStream = context.resources.openRawResource(R.raw.passe_batalha)
//            val jsonStr: String = inputStream.bufferedReader().use { it.readText() }
//            JSONObject(jsonStr)
//        }catch (e: IOException) {
//            JSONObject()
//        }
//    }

    fun getPassBattle(): BattlePass {
        return passBattle
    }
}