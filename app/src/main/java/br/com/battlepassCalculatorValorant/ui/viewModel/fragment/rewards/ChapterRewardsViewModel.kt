package br.com.battlepassCalculatorValorant.ui.viewModel.fragment.rewards

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.battlepassCalculatorValorant.database.room.model.UserTier
import br.com.battlepassCalculatorValorant.model.battlePass.Chapter
import br.com.battlepassCalculatorValorant.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChapterRewardsViewModel @Inject constructor(calculador: CalculatorRepository) :
    ViewModel() {

    val chapters: ArrayList<Chapter> = calculador.listChapters
    val lastChapter: LiveData<UserTier> = calculador.lastUserInput.asLiveData()


}