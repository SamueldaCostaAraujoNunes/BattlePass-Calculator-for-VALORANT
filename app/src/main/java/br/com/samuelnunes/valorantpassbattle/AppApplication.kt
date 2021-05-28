package br.com.samuelnunes.valorantpassbattle

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import br.com.samuelnunes.valorantpassbattle.database.dataStore.SettingsDataStore
import br.com.samuelnunes.valorantpassbattle.util.CrashlyticsTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = KEY_DATA_STORE)

@HiltAndroidApp
class BattlePassCalculatorApp : Application() {
    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(
            settingsDataStore.getInt(
                KEY_THEME,
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            )
        )
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashlyticsTree())
        }

    }
}

