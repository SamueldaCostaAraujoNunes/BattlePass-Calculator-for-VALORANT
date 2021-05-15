package br.com.battlepassCalculatorValorant

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import br.com.battlepassCalculatorValorant.database.SharedPreferences.MSharedPreferences
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class BattlePassCalculatorApp : Application() {
    @Inject
    lateinit var mSharedPreferences: MSharedPreferences
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(mSharedPreferences[KEY_THEME, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM])
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

