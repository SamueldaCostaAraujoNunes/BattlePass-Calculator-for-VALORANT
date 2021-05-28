package br.com.samuelnunes.valorantpassbattle.ui.viewModel.fragment.infos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import br.com.samuelnunes.valorantpassbattle.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class TimelineFragmentViewModel @Inject constructor(calculador: CalculatorRepository) :
    ViewModel() {

    val dataInicioAto: String = calculador.openingDateOfTheAct
    val dataFinalizacao: LiveData<String> = calculador.expectedEndDay.asLiveData().map {
        it.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }
    val dataFinalAto: String = calculador.closingDateOfTheAct
    val diaAtualDoPasse: Int = calculador.daysFromTheStart
    val diasParaOFimDoPasse: Int = calculador.daysLeftUntilTheEnd
    val diasAdiantadoAtrasado: LiveData<Long> = calculador.daysMissing.asLiveData()
}