package br.com.samuelnunes.valorantpassbattle.ui.viewModel.fragment.rewards

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.samuelnunes.valorantpassbattle.model.dto.Reward
import br.com.samuelnunes.valorantpassbattle.model.dto.UserTier
import br.com.samuelnunes.valorantpassbattle.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TiersRewardsViewModel @Inject constructor(calculador: CalculatorRepository) :
    ViewModel() {

    val tiers: List<Reward> = calculador.listTiers
    val lastTier: LiveData<UserTier> = calculador.lastUserInput.asLiveData()


}