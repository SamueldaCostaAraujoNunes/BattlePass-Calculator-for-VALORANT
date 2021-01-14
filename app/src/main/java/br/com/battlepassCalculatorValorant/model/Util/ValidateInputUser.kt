package br.com.battlepassCalculatorValorant.model.Util

import android.content.Context
import android.widget.TextView
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Properties.Properties

class ValidateInputUser(
    val context: Context,
    val tvTierIndex: TextView,
    val tvTierExpMissing: TextView
) {
    private val properties = Properties(context)

    fun isValide(): Boolean {
        if (tierIndex()) {
            if (tierExpMissing()) {
                return true
            }
        }
        return false
    }

    private fun tierIndex(): Boolean {
        val tierInputCurrent =
            if (properties.historic.isEmpty()) null else properties.historic.last()
        val tierCurrent = properties.passBattle.getTier(tierInputCurrent?.tierCurrent ?: 1)
        val index = tierCurrent?.index ?: 0
        val tierStr = tvTierIndex.text.toString()
        if (tierStr.isEmpty()) {
            tvTierIndex.error = context.getString(R.string.insira_um_tier)
        } else {
            val tierMax = properties.passBattle.tiers.last().index
            val tierInt = tierStr.toInt()
            if ((tierInt < index) or (tierInt > tierMax)) {
                tvTierIndex.error = context.getString(R.string.tier_intervalo, index, tierMax)
            }
        }
        return tvTierIndex.error == null
    }

    fun tierExpMissing(): Boolean {
        val tier = properties.passBattle.getTier(tvTierIndex.text.toString().toInt())!!
        val tierStr = tvTierExpMissing.text.toString()
        if (tierStr.isEmpty()) {
            tvTierExpMissing.error = context.getString(R.string.insira_o_exp_faltando)
        } else {
            val tierInt = tierStr.toInt()
            var ultimoXp = tier.expMissing
            val ultimoTier =
                if (properties.historic.isEmpty()) 0 else properties.historic.last().tierCurrent
            if (tier.index == ultimoTier) {
                ultimoXp =
                    if (properties.historic.isEmpty()) 0 else properties.historic.last().tierExpMissing
            }
            val zero = 0
            if ((tierInt < zero) or (tierInt > ultimoXp)) tvTierExpMissing.error =
                context.getString(R.string.exp_intervalo, zero, ultimoXp)
        }
        return tvTierExpMissing.error == null
    }

    fun editTierExpMissing(): Boolean {
        val tierStr = tvTierExpMissing.text.toString()
        if (tierStr.isEmpty()) {
            tvTierExpMissing.error = context.getString(R.string.insira_o_exp_faltando)
        } else {
            val tierInt = tierStr.toInt()
            val ultimoXp =
                properties.passBattle.getTier(tvTierIndex.text.toString().toInt())!!.expMissing
            val zero = 0
            if ((tierInt < zero) or (tierInt > ultimoXp)) tvTierExpMissing.error =
                context.getString(R.string.exp_intervalo, zero, ultimoXp)
        }
        return tvTierExpMissing.error == null
    }
}