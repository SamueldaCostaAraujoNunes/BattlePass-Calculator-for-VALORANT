package br.com.battlepassCalculatorValorant.ui.viewModel.dialog

import androidx.lifecycle.ViewModel
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.battlePass.Reward
import br.com.battlepassCalculatorValorant.repository.CalculatorRepository
import br.com.battlepassCalculatorValorant.ui.view.fragment.Rewards.CHAPTER
import br.com.battlepassCalculatorValorant.ui.view.fragment.Rewards.TIER
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