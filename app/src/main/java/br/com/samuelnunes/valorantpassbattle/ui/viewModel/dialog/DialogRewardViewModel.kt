package br.com.samuelnunes.valorantpassbattle.ui.viewModel.dialog

import androidx.lifecycle.ViewModel
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.model.dto.Reward
import br.com.samuelnunes.valorantpassbattle.repository.CalculatorRepository
import br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Rewards.CHAPTER
import br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Rewards.TIER
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DialogRewardViewModel @Inject constructor(private val calculador: CalculatorRepository) :
    ViewModel() {

    fun getRewardById(id: Int, origin: Int): Reward? {
        return when (origin) {
            TIER -> calculador.getRewardTiersById(id)
            CHAPTER -> calculador.getRewardChapterById(id)
            else -> null
        }
    }

    fun getTitle(origin: Int): Int {
        return when (origin) {
            TIER -> {
                R.string.tier
            }
            CHAPTER -> {
                R.string.capitulo
            }
            else -> {
                0
            }
        }
    }
}