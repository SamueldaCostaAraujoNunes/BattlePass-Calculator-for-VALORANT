package br.com.battlepassCalculatorValorant.ui.view.fragment.Rewards

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.map
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.ui.viewModel.fragment.rewards.ChapterRewardsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChapterRewards : BaseRewardsFragment() {
    private val viewModel: ChapterRewardsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rewards = viewModel.chapters
        positionTier = viewModel.lastChapter.map { (it.tierCurrent / 5) + 1 }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun toString(): String {
        return context?.getString(R.string.recompensa_por_capitulo) ?: super.toString()
    }
}