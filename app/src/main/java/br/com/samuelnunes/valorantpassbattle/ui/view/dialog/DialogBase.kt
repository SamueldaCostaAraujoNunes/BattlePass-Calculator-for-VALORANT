package br.com.samuelnunes.valorantpassbattle.ui.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import br.com.samuelnunes.valorantpassbattle.ui.view.activity.BaseActivity
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

open class DialogBase : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun showSnack(string: String, duration: Int = Snackbar.LENGTH_SHORT) {
        getSnack(string, duration)?.show()
    }

    private fun getSnack(string: String, duration: Int = Snackbar.LENGTH_SHORT): Snackbar? {
        val activity = activity
        return if (activity is BaseActivity) {
            activity.getSnackInstance(string, duration)
        } else {
            Timber.w("Not part of BaseActivity")
            null
        }
    }
}