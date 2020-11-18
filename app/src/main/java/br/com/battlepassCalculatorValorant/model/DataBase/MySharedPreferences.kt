package br.com.battlepassCalculatorValorant.model.DataBase

import android.content.Context
import android.content.SharedPreferences
import br.com.battlepassCalculatorValorant.model.Historic.UserInputsTier
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Suppress("UNREACHABLE_CODE")
class MySharedPreferences(context: Context) {
    val keySharedPreferences = "MySharedPreferencesKey"
    val keyHistoricUserInputsPassBattle = "keyHistoricUserInputsPassBattle"
    val keyFirstInputComplete = "keyFirstInputComplete"
    val keyMissoesDiarias = "keyMissoesDiarias"
    val keyMissoesSemanais = "keyMissoesSemanais"


    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        keySharedPreferences,
        Context.MODE_PRIVATE
    )

    private val editor = sharedPreferences.edit()

    fun <T> setList(key: String, list: List<T>) {
        val gson = Gson()
        val type = object : TypeToken<List<T?>?>() {}.type
        val json = gson.toJson(list, type)
        set(key, json)
    }

    fun <T> getList(key: String): List<T> {
        val arrayItems: List<T>
        val serializedObject = get(key)
        if (serializedObject != null) {
            val gson = Gson()
            val type = object : TypeToken<List<T>>() {}.type
            arrayItems = gson.fromJson(serializedObject, type)
            return arrayItems
        }
        return ArrayList<T>()
    }

    fun setListHistoric(list: List<UserInputsTier>) {
        val gson = Gson()
        val type = object : TypeToken<List<UserInputsTier>>() {}.type
        val json = gson.toJson(list, type)
        set(keyHistoricUserInputsPassBattle, json)
    }

    fun getListHistoric(): List<UserInputsTier> {
        val arrayItems: List<UserInputsTier>
        val serializedObject = get(keyHistoricUserInputsPassBattle)
        if (serializedObject != null) {
            val gson = Gson()
            val type = object : TypeToken<List<UserInputsTier>>() {}.type
            arrayItems = gson.fromJson(serializedObject, type)
            return arrayItems
        }
        return ArrayList<UserInputsTier>()
    }

    private operator fun set(key: String?, value: String?) {
        editor.putString(key, value)
        editor.commit()
    }

    private operator fun get(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun setBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.commit()
    }
}