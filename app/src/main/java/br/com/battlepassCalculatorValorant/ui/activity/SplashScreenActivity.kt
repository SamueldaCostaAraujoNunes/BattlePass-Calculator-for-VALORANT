package br.com.battlepassCalculatorValorant.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.com.battlepassCalculatorValorant.BuildConfig
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.ListListener.HistoricArrayListListener
import br.com.battlepassCalculatorValorant.ui.theme.Theme


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val theme = Theme(this)
        theme.checkTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val handle = Handler()
        handle.postDelayed(Runnable { mostrarProxActivity() }, if (BuildConfig.DEBUG) 100 else 1100)

    }

    private fun mostrarProxActivity() {
        val db = HistoricArrayListListener(this)
        val intent = if (db.isEmpty()) {
            Intent(this@SplashScreenActivity, FirstInputActivity::class.java)
        } else {
            Intent(this@SplashScreenActivity, MainActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}