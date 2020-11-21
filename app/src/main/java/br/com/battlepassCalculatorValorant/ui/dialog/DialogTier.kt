package br.com.battlepassCalculatorValorant.ui.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.PassBattle.Tier
import kotlinx.android.synthetic.main.dialog_tier.view.*
import kotlinx.android.synthetic.main.dialog_title.view.*

@SuppressLint("InflateParams")
class DialogTier(context: Context, tier: Tier) : AlertDialog(context) {
    var builder: Builder

    init {
        builder = Builder(context)
            .setView(createContent(tier))
            .setCustomTitle(createTitle("Tier ${tier.index}"))
    }

    private fun createTitle(title: String): View {
        val titleView: View = this.layoutInflater.inflate(R.layout.dialog_title, null)
        titleView.title.text = title
        return titleView
    }

    private fun createContent(tier: Tier): View {
        val mDialogView = this.layoutInflater.inflate(R.layout.dialog_tier, null)
        with(mDialogView) {
            val expInitial = tier.expInitial.toString() + " EXP"
            val expMissing = tier.expMissing.toString() + " EXP"
            val totalPercentage = tier.totalPercentage.toString() + "%"
            val reward = tier.reward

            tv_exp_initial_tier.text = expInitial
            tv_exp_missing_tier.text = expMissing
            tv_total_percentage_tier.text = totalPercentage
            tv_reward_tier.text = reward
        }
        return mDialogView
    }

    override fun show() {
        builder.show()
    }


}