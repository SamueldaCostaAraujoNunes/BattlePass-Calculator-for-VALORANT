package br.com.battlepassCalculatorValorant.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Historic.Historic
import br.com.battlepassCalculatorValorant.ui.Advertisement.Advertisement
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.dialog_confirmation.view.*
import kotlinx.android.synthetic.main.dialog_tierinput.view.tierinput_dialog_btn_cancel
import kotlinx.android.synthetic.main.dialog_tierinput.view.tierinput_dialog_btn_save

class DialogConfimation(context: Context, val position: Int, val historic: Historic) :
    AlertDialog(context) {
    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var adv: Advertisement
    var mDialogView: View
    val tier = historic[position]
    var builder: AlertDialog.Builder
    lateinit var dialog: AlertDialog

    init {
        val inflater = this.layoutInflater

        mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_confirmation, null)

        mDialogView.tierinput_dialog_btn_save.text = context.getString(R.string.deletar)
        val textConfirmation = context.getString(R.string.text_delete) + " ${tier.tierCurrent}?"
        mDialogView.tv_confirmation.text = textConfirmation
        builder = Builder(context)
            .setView(mDialogView)
//            .setCustomTitle(createTitle(context.getString(R.string.confirmacao)))
            .setTitle(context.getString(R.string.confirmacao))
        adv = Advertisement(context)
        mInterstitialAd = adv.createInterstitial()
    }

    fun show(function: () -> Unit) {
        dialog = builder.show()
        setOnClickListener(function)
    }

//    private fun createTitle(title: String): View {
//        val titleView: View = this.layoutInflater.inflate(R.layout.dialog_title, null)
//        titleView.title.text = title
//        return titleView
//    }

    fun setOnClickListener(function: () -> Unit) {
        mDialogView.tierinput_dialog_btn_save.setOnClickListener {
            val listItem = historic[position]
            historic.delete(listItem.tierCurrent)
            function()
            dialog.dismiss()
        }
        mDialogView.tierinput_dialog_btn_cancel.setOnClickListener {
            dialog.dismiss()
        }
    }
}