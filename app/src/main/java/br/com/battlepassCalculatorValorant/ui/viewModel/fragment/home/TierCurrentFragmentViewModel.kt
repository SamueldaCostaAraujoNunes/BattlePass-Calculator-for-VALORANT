package br.com.battlepassCalculatorValorant.ui.viewModel.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import br.com.battlepassCalculatorValorant.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TierCurrentFragmentViewModel @Inject constructor(calculador: CalculatorRepository) :
    ViewModel() {
    val percentageTier: LiveData<Double> = calculador.percentageTier.asLiveData()
    val tierIndex: LiveData<Int> = calculador.tierCurrent.asLiveData().map { it.index }
    val tierName: LiveData<String> = calculador.tierCurrent.asLiveData().map { it.reward[0].nome }
    val imagesURL: LiveData<List<String>> =
        calculador.tierCurrent.asLiveData().map { it.reward[0].imagens }
}
