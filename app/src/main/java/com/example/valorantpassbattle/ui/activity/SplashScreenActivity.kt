package com.example.valorantpassbattle.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.model.DataBase.MySharedPreferences


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val handle = Handler()
        handle.postDelayed(Runnable { mostrarProxActivity() }, 1100)
    }

    private fun mostrarProxActivity() {
        val bd = MySharedPreferences(this)
        val intent = if (bd.getBoolean(bd.keyFirstInputComplete)) {
            Intent(this@SplashScreenActivity, MainActivity::class.java)
        } else {
            Intent(this@SplashScreenActivity, FirstInputActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}