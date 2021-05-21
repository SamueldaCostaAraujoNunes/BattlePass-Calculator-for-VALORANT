package br.com.battlepassCalculatorValorant

import androidx.datastore.preferences.core.booleanPreferencesKey


const val KEY_THEME = "keyTheme"

// BD
const val BD_APP_NAME = "valorantCalculator.db"
const val BD_APP_VERSION = 1

// DataStore

const val KEY_DATA_STORE = "dataStoreSettings"

val KEY_EPILOGUE = booleanPreferencesKey("keyEpilogue")
val KEY_DAILY_MISSION = booleanPreferencesKey("keyDailyMission")
val KEY_WEEKLY_MISSION = booleanPreferencesKey("keyWeeklyMission")