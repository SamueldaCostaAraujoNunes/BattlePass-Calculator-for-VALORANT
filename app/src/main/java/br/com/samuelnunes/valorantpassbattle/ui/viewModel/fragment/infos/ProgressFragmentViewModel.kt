package br.com.samuelnunes.valorantpassbattle.ui.viewModel.fragment.infos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.samuelnunes.valorantpassbattle.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProgressFragmentViewModel @Inject constructor(calculador: CalculatorRepository) :
    ViewModel() {

    val totalExp: LiveData<Int> = calculador.expCurrent.asLiveData()
    val porcentagemProgresso: LiveData<Double> = calculador.percentageTotal.asLiveData()
    val expPerDay: LiveData<Int> = calculador.averageExpPerDay.asLiveData()
    val expAdiantadoAtrasado: LiveData<Int> =
        calculador.differenceBetweenTheExpectedExpWithTheCurrent.asLiveData()


}