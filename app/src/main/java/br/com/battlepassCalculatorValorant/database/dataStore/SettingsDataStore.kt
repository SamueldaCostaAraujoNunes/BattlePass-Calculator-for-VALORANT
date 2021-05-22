package br.com.battlepassCalculatorValorant.database.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.preference.PreferenceDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SettingsDataStore @Inject constructor(private val dataStore: DataStore<Preferences>) :
    PreferenceDataStore() {

    override fun putString(key: String, value: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { it[stringPreferencesKey(key)] = value!! }
        }
    }

    override fun getString(key: String, defValue: String?): String {
        return runBlocking {
            dataStore.data.map { it[stringPreferencesKey(key)] ?: defValue!! }.first()
        }.toString()
    }

    override fun putBoolean(key: String, value: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { it[booleanPreferencesKey(key)] = value }
        }
    }

    override fun getBoolean(key: String, defValue: Boolean): Boolean {
        return runBlocking {
            dataStore.data.map { it[booleanPreferencesKey(key)] ?: defValue }.first()
        }
    }

    override fun putInt(key: String, value: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { it[intPreferencesKey(key)] = value }
        }
    }

    override fun getInt(key: String, defValue: Int): Int {
        return runBlocking {
            dataStore.data.map { it[intPreferencesKey(key)] ?: defValue }.first()
        }
    }
}