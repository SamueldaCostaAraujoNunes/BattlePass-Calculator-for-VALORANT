package br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Rewards

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.map
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.fragment.rewards.ChapterRewardsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChapterRewards : BaseRewardsFragment() {
    private val viewModel: ChapterRewardsViewModel by viewModels()

    override val origin: Int = CHAPTER
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rewards = viewModel.chapters
        positionTier = viewModel.lastChapter.map { (it.tierCurrent / 5) + 1 }
        super.onViewCreated(view, savedInstanceState)
    }

    override val titleResId: Int
        get() = R.string.recompensa_por_capitulo
}

interface Title