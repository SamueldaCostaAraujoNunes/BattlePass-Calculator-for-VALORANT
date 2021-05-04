package br.com.battlepassCalculatorValorant.ui.view.fragment.RewardsRecycler

import android.os.Bundle
import android.view.View
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.model.Singleton.ManagerProperties
import br.com.battlepassCalculatorValorant.model.battlePass.Reward

class TiersRewards : BaseRewardsFragment() {
    lateinit var properties: Properties

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        properties = ManagerProperties.getInstance(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val lista = ArrayList<Reward>()
        for (capitulo in properties.getListTiers()) {
            lista += capitulo.reward
        }
        rewards = lista
        positionCurrent = properties.getTierCurrent()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun toString(): String {
        return context?.getString(R.string.recompensa_por_tier) ?: super.toString()
    }
}