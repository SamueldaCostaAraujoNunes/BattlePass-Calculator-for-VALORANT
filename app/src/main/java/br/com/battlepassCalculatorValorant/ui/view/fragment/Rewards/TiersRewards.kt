package br.com.battlepassCalculatorValorant.ui.view.fragment.Rewards

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.map
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.battlePass.Reward
import br.com.battlepassCalculatorValorant.ui.viewModel.fragment.rewards.TiersRewardsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TiersRewards : BaseRewardsFragment() {
    private val viewModel: TiersRewardsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val auxRewards = arrayListOf<Reward>()
        for (tier in viewModel.tiers) {
            auxRewards += tier.reward
        }
        rewards = auxRewards
        positionTier = viewModel.lastTier.map { it.tierCurrent }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun toString(): String {
        return context?.getString(R.string.recompensa_por_tier) ?: super.toString()
    }
}