package br.com.battlepassCalculatorValorant.ui.viewModel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.battlepassCalculatorValorant.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimelineFragmentViewModel @Inject constructor(calculador: CalculatorRepository) :
    ViewModel() {

    val dataInicioAto: String = calculador.dataInicioAto
    val dataFinalizacao: LiveData<String> = calculador.finishForecast
    val dataFinalAto: String = calculador.dataFinalAto
    val diaAtualDoPasse: Int = calculador.days
    val diasParaOFimDoPasse: Int = calculador.daysForClosed
    val diasAdiantadoAtrasado: LiveData<Int> = calculador.daysMissing

//    val dateInit = properties.passBattle.getDateInit()
//    val dateFinally = properties.passBattle.getDateFinally()
//    val finishForecast = properties.finishForecast()
//    val daysMissing = properties.daysMissing()!!
//    val dayCurrent = properties.dayCurrent()
//    val daysForClosed = properties.daysForClosed()

}