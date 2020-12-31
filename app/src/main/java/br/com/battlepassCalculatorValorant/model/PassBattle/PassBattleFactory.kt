package br.com.battlepassCalculatorValorant.model.PassBattle

import android.content.Context
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class PassBattleFactory(val context: Context){
    private val PATH_INFOS = "passe_batalha.json"
    private val json = readJson()
    private val passBattle = PassBattle(json)

    fun readJson(): JSONObject {
        try {
            val inputStream: InputStream = context.assets.open(PATH_INFOS)
            val jsonStr: String = inputStream.bufferedReader().use { it.readText() }
            val json = JSONObject(jsonStr)
            return json
        }catch(e: IOException) {
            return JSONObject()
        }
    }

    fun getPassBattle(): PassBattle {
        return passBattle
    }
}