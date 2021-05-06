package br.com.battlepassCalculatorValorant.ui.viewModel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.battlepassCalculatorValorant.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProgressFragmentViewModel @Inject constructor(calculador: CalculatorRepository) :
    ViewModel() {

    val totalExp: LiveData<Int> = calculador.totalExpCurrent.asLiveData()
    val porcentagemProgresso: LiveData<Double> = calculador.percentageTotal.asLiveData()
    val expPerDay: LiveData<Int> = calculador.averageExpPerDay.asLiveData()
    val expAdiantadoAtrasado: LiveData<Int> =
        calculador.differenceBetweenTheExpectedExpWithTheCurrent.asLiveData()


}