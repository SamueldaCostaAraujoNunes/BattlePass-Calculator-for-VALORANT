package br.com.samuelnunes.valorantpassbattle.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity() {
    open fun showSnack(string: String, duration: Int = Snackbar.LENGTH_SHORT) {
        getSnackInstance(string, duration).show()
    }

    abstract fun getSnackInstance(string: String, duration: Int = Snackbar.LENGTH_SHORT): Snackbar
}