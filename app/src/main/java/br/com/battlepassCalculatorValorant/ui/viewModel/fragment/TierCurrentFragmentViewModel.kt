package br.com.battlepassCalculatorValorant.ui.viewModel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import br.com.battlepassCalculatorValorant.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TierCurrentFragmentViewModel @Inject constructor(calculador: CalculatorRepository) :
    ViewModel() {
    val percentageTier: LiveData<Double> = calculador.percentageTier
    val tierIndex: LiveData<Int> = calculador.tierCurrent.map { it.index }
    val tierName: LiveData<String> = calculador.tierCurrent.map { it.reward[0].nome }
    val imagesURL: LiveData<List<String>> = calculador.tierCurrent.map { it.reward[0].imagens }
}
