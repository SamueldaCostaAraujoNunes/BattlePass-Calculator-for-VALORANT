package br.com.battlepassCalculatorValorant.ui.viewModel.fragment.infos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.battlepassCalculatorValorant.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimelineFragmentViewModel @Inject constructor(calculador: CalculatorRepository) :
    ViewModel() {

    val dataInicioAto: String = calculador.openingDateOfTheAct
    val dataFinalAto: String = calculador.closingDateOfTheAct
    val diaAtualDoPasse: Int = calculador.daysFromTheStart
    val diasParaOFimDoPasse: Int = calculador.daysLeftUntilTheEnd
    val dataFinalizacao: LiveData<String> = calculador.finishForecast.asLiveData()
    val diasAdiantadoAtrasado: LiveData<Int> = calculador.daysMissing.asLiveData()

//    val dateInit = properties.passBattle.getDateInit()
//    val dateFinally = properties.passBattle.getDateFinally()
//    val finishForecast = properties.finishForecast()
//    val daysMissing = properties.daysMissing()!!
//    val dayCurrent = properties.dayCurrent()
//    val daysForClosed = properties.daysForClosed()

}