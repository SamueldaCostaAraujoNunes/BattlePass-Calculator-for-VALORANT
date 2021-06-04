package br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Rewards

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.map
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.fragment.rewards.TiersRewardsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TiersRewards : BaseRewardsFragment() {
    private val viewModel: TiersRewardsViewModel by viewModels()
    override val origin: Int = TIER

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rewards = viewModel.tiers
        positionTier = viewModel.lastTier.map { it.tierCurrent }
        super.onViewCreated(view, savedInstanceState)
    }

    override val titleResId: Int
        get() = R.string.recompensa_por_tier


}