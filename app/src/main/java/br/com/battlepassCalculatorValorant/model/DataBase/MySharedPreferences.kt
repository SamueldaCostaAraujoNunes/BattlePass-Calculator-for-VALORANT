package br.com.battlepassCalculatorValorant.model.DataBase

import android.content.Context
import android.content.SharedPreferences


@Suppress("UNREACHABLE_CODE")
class MySharedPreferences(context: Context) {
    val keySharedPreferences = "MySharedPreferencesKey"
    val keyFirstInputComplete = "keyFirstInputComplete"
    val keyThemeStatus = "keyThemeStatus"
    val keyHistoricUserInputsPassBattle = "keyHistoricUserInputsPassBattle"

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        keySharedPreferences,
        Context.MODE_PRIVATE
    )
    private val editor = sharedPreferences.edit()

    fun deleteBD() {
        sharedPreferences.edit().remove(keyHistoricUserInputsPassBattle).apply()
    }

    var darkMode = getInt(keyThemeStatus, 2)
        set(value) = setInt(keyThemeStatus, value)

    operator fun set(key: String?, value: String?) {
        editor.putString(key, value)
        editor.commit()
    }

    operator fun get(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun setBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.commit()
    }

    private fun getInt(key: String, default: Int = 0): Int {
        return sharedPreferences.getInt(key, default)
    }

    private fun setInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.commit()
    }

}