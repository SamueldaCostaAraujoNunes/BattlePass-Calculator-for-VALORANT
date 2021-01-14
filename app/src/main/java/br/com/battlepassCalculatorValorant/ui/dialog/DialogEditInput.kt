package br.com.battlepassCalculatorValorant.ui.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Historic.Historic
import br.com.battlepassCalculatorValorant.model.SingletonPassBattle.ManagerProperties
import br.com.battlepassCalculatorValorant.model.Util.ValidateInputUser
import br.com.battlepassCalculatorValorant.ui.Advertisement.Advertisement
import com.google.android.gms.ads.InterstitialAd
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.dialog_tierinput.view.*

@Suppress("CAST_NEVER_SUCCEEDS")
class DialogEditInput(context: Context, val position: Int, val historic: Historic) :
    AlertDialog(context) {

    private val passBattle = ManagerProperties.getInstance(context).passBattle

    private var mInterstitialAd: InterstitialAd
    var tvTierIndex: TextInputEditText
    var tvTierExpMissing: TextInputEditText
    var mDialogView: View
    val tier = historic[position]
    var builder: AlertDialog.Builder
    lateinit var dialog: AlertDialog

    init {
        mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_tierinput, null)

        tvTierIndex = mDialogView.tierinput_dialog_et_level_current
        tvTierExpMissing = mDialogView.tierinput_dialog_et_exp_missing

        tvTierIndex.setText(tier.tierCurrent.toString())
        tvTierIndex.isEnabled = false
        tvTierExpMissing.setText(tier.tierExpMissing.toString())

        builder = Builder(context)
            .setView(mDialogView)
//            .setCustomTitle(createTitle("Tier Edit"))
            .setTitle(context.getString(R.string.tier_edit))
        mInterstitialAd = Advertisement(context).createInterstitial()
    }

//    private fun createTitle(title: String): View {
//        val titleView: View = this.layoutInflater.inflate(R.layout.dialog_title, null)
//        titleView.title.text = title
//        return titleView
//    }

    override fun show() {
        dialog = builder.show()
        setOnClickListener()
    }

    fun setOnClickListener() {
        mDialogView.tierinput_dialog_btn_save.setOnClickListener {
            val validador = ValidateInputUser(context, tvTierIndex, tvTierExpMissing)
            if (validador.editTierExpMissing()) {
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

}