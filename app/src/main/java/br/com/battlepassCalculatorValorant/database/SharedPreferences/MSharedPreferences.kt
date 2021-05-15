package br.com.battlepassCalculatorValorant.database.SharedPreferences

import android.content.Context
import android.content.SharedPreferences

class MSharedPreferences(context: Context) {
    private val keySharedPreferences = "MySharedPreferencesKey"

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        keySharedPreferences,
        Context.MODE_PRIVATE
    )
    private val editor = sharedPreferences.edit()

    // String
    fun set(key: String?, value: String?) {
        editor.putString(key, value)
        editor.apply()
    }

    // Boolean
    fun set(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    // Int
    fun set(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    // String
    operator fun get(key: String, defValue: String? = null): String? {
        return sharedPreferences.getString(key, defValue)
    }

    // Boolean
    operator fun get(key: String, defValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    // Int
    operator fun get(key: String, default: Int = 0): Int {
        return sharedPreferences.getInt(key, default)
    }
}