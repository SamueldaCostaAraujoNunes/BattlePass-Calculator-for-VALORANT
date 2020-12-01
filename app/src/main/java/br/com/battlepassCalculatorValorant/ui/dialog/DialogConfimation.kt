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
import kotlinx.android.synthetic.main.dialog_title.view.*

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

        val titleView: View = inflater.inflate(R.layout.dialog_title, null)
        titleView.title.text = "Confirmacao"
        mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_confirmation, null)

        mDialogView.tierinput_dialog_btn_save.text = "Deletar"
        mDialogView.tv_confirmation.text = "Deseja deletar o Tier ${tier.tierCurrent}?"

        builder = Builder(context).setView(mDialogView).setCustomTitle(titleView)
        adv = Advertisement(context)
        mInterstitialAd = adv.createInterstitial()
    }

    fun show(function: () -> Unit) {
        dialog = builder.show()
        setOnClickListener(function)
    }

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