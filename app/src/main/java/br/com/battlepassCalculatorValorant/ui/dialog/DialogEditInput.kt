package br.com.battlepassCalculatorValorant.ui.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Historic.Historic
import br.com.battlepassCalculatorValorant.model.SingletonPassBattle.ManagerProperties
import br.com.battlepassCalculatorValorant.ui.Advertisement.Advertisement
import com.google.android.gms.ads.InterstitialAd
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.dialog_tierinput.view.*
import kotlinx.android.synthetic.main.dialog_title.view.*

@Suppress("CAST_NEVER_SUCCEEDS")
class DialogEditInput(context: Context, val position: Int, val historic: Historic) :
    AlertDialog(context) {

    private val passBattle = ManagerProperties.getInstance(context).passBattle

    private var mInterstitialAd: InterstitialAd
    private var adv: Advertisement
    var tvTierIndex: TextInputEditText
    var tvTierExpMissing: TextInputEditText
    var mDialogView: View
    val tier = historic[position]
    var builder: AlertDialog.Builder
    lateinit var dialog: AlertDialog

    init {
        val inflater = this.layoutInflater

        val titleView: View = inflater.inflate(R.layout.dialog_title, null)
        titleView.title.text = "Tier Edit"

        mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_tierinput, null)

        tvTierIndex = mDialogView.tierinput_dialog_et_level_current
        tvTierExpMissing = mDialogView.tierinput_dialog_et_exp_missing

        tvTierIndex.setText(tier.tierCurrent.toString())
        tvTierIndex.isEnabled = false
        tvTierExpMissing.setText(tier.tierExpMissing.toString())

        builder = Builder(context).setView(mDialogView).setCustomTitle(titleView)
        adv = Advertisement(context)
        mInterstitialAd = adv.createInterstitial()
    }

    override fun show() {
        dialog = builder.show()
        setOnClickListener()
    }

    fun setOnClickListener() {
        mDialogView.tierinput_dialog_btn_save.setOnClickListener {
            val tierInputCurrent =
                if (historic.isEmpty()) null else historic.last()
            val tierCurrent = passBattle.getTier(tierInputCurrent?.tierCurrent ?: 1)
            if (validadeTierExpMissing(tvTierExpMissing, tierCurrent?.expMissing ?: 50000)) {
                tier.tierExpMissing = tvTierExpMissing.text.toString().toInt()
                historic.update(tier)
                dialog.dismiss()
                launcherAdMob()
            }
        }
        mDialogView.tierinput_dialog_btn_cancel.setOnClickListener {
            dialog.dismiss()
        }
    }

    fun launcherAdMob() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.")
        }
    }

    fun validadeTierExpMissing(tv: TextView, expMissing: Int): Boolean {
        val tierStr = tv.text.toString()
        if (tierStr == "") tv.error = "Insira o EXP faltando!"
        if (tierStr.isNotEmpty()) {
            if (tierStr.length <= 5) {
                val tierInt = tierStr.toInt()
                if ((tierInt < 0) or (tierInt > expMissing)) tv.error =
                    "Insira um EXP entre 0 e $expMissing!"
            } else {
                tv.error = "Insira um EXP menor ou igual a $expMissing!"
            }
        }
        return tv.error == null
    }
}