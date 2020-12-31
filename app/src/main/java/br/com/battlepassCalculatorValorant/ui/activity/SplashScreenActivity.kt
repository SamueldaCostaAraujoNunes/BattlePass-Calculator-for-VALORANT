package br.com.battlepassCalculatorValorant.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import br.com.battlepassCalculatorValorant.BuildConfig
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.ListListener.HistoricArrayListListener
import br.com.battlepassCalculatorValorant.ui.activity.IntroTutorial.IntroTutorialActivity
import br.com.battlepassCalculatorValorant.ui.theme.Theme


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val theme = Theme(this)
        theme.checkTheme()
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash_screen)
        val handle = Handler()
        handle.postDelayed(Runnable { mostrarProxActivity() }, if (BuildConfig.DEBUG) 100 else 1100)

    }

    private fun mostrarProxActivity() {
        val db = HistoricArrayListListener(this)
        val intent = if (db.isEmpty()) {
            Intent(this@SplashScreenActivity, IntroTutorialActivity::class.java)
        } else {
            Intent(this@SplashScreenActivity, IntroTutorialActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}