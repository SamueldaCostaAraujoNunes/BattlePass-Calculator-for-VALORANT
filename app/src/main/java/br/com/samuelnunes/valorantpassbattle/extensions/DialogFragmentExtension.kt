package br.com.samuelnunes.valorantpassbattle.extensions

import androidx.fragment.app.DialogFragment
import br.com.samuelnunes.valorantpassbattle.ui.view.activity.BaseActivity
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

/**
 * @author Samuel da Costa Araujo Nunes
 * Created 09/07/2021 at 17:02
 */
fun DialogFragment.showSnack(string: String, duration: Int = Snackbar.LENGTH_SHORT) {
    getSnack(string, duration)?.show()
}

private fun DialogFragment.getSnack(
    string: String,
    duration: Int = Snackbar.LENGTH_SHORT
): Snackbar? {
    val activity = activity
    return if (activity is BaseActivity) {
        activity.getSnackInstance(string, duration)
    } else {
        Timber.w("Not part of BaseActivity")
        null
    }
}