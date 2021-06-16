package br.com.samuelnunes.valorantpassbattle.ui.viewModel.fragment.infos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.samuelnunes.valorantpassbattle.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TimelineFragmentViewModel @Inject constructor(
    val calculador: CalculatorRepository
) : ViewModel() {
    val dataInicioAto: LocalDate = calculador.openingDateOfTheAct
    val dataFinalizacao: LiveData<LocalDate> = calculador.expectedEndDay.asLiveData()
    val dataFinalAto: LocalDate = calculador.closingDateOfTheAct
    val diaAtualDoPasse: Int = calculador.daysFromTheStart
    val diasParaOFimDoPasse: Int = calculador.daysLeftUntilTheEnd
    val diasAdiantadoAtrasado: LiveData<Long> = calculador.daysMissing.asLiveData()
}