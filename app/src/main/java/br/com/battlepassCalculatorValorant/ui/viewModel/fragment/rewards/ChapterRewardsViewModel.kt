package br.com.battlepassCalculatorValorant.ui.viewModel.fragment.rewards

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.battlepassCalculatorValorant.database.room.model.UserTier
import br.com.battlepassCalculatorValorant.model.newBattlePass.Reward
import br.com.battlepassCalculatorValorant.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChapterRewardsViewModel @Inject constructor(calculador: CalculatorRepository) :
    ViewModel() {

    val chapters: List<Reward> = calculador.listChapters
    val lastChapter: LiveData<UserTier> = calculador.lastUserInput.asLiveData()


}