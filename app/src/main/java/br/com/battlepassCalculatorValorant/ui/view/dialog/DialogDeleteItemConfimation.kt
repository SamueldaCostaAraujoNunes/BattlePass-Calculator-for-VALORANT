package br.com.battlepassCalculatorValorant.ui.view.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Historic.Historic
import br.com.battlepassCalculatorValorant.ui.view.Advertisement.Advertisement
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.dialog_confirmation.view.*
import kotlinx.android.synthetic.main.dialog_tierinput.view.tierinput_dialog_btn_cancel
import kotlinx.android.synthetic.main.dialog_tierinput.view.tierinput_dialog_btn_save

class DialogDeleteItemConfimation(context: Context, val position: Int, val historic: Historic) :
    AlertDialog(context) {
    private var mInterstitialAd: InterstitialAd
    var mDialogView: View
    val tier = historic[position]
    var builder: AlertDialog.Builder
    lateinit var dialog: AlertDialog

    init {
        mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_confirmation, null)

        mDialogView.tierinput_dialog_btn_save.text = context.getString(R.string.deletar)
        val textConfirmation = context.getString(R.string.text_delete) + " ${tier.tierCurrent}?"
        mDialogView.tv_confirmation.text = textConfirmation
        builder = Builder(context)
            .setView(mDialogView)
            .setTitle(context.getString(R.string.confirmacao))
        mInterstitialAd = Advertisement(context).createInterstitial()
    }

    fun show(functionSave: () -> Unit, functionCancel: () -> Unit) {
        dialog = builder.show()
        setOnClickListener(functionSave, functionCancel)
    }

    fun setOnClickListener(functionSave: () -> Unit, functionCancel: () -> Unit) {
        mDialogView.tierinput_dialog_btn_save.setOnClickListener {
            val listItem = historic[position]
            historic.delete(listItem.tierCurrent)
            functionSave()
            dialog.dismiss()
            launcherAdMob()
        }
        mDialogView.tierinput_dialog_btn_cancel.setOnClickListener {
            functionCancel()
            dialog.dismiss()
        }
    }

    private fun launcherAdMob() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.")
        }
    }
}