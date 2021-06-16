package br.com.samuelnunes.valorantpassbattle.ui.viewModel.fragment.infos

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import br.com.samuelnunes.valorantpassbattle.extensions.formatterLocale
import br.com.samuelnunes.valorantpassbattle.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class TimelineFragmentViewModel @Inject constructor(
    @ApplicationContext context: Context,
    val calculador: CalculatorRepository
) :
    ViewModel() {

    val dataInicioAto: String = calculador.openingDateOfTheAct.formatterLocale(context)
    val dataFinalizacao: LiveData<String> = calculador.expectedEndDay.asLiveData().map {
        it.formatterLocale(context)
    }
    val dataFinalAto: String = calculador.closingDateOfTheAct.formatterLocale(context)

    val diaAtualDoPasse: Int = calculador.daysFromTheStart
    val diasParaOFimDoPasse: Int = calculador.daysLeftUntilTheEnd
    val diasAdiantadoAtrasado: LiveData<Long> = calculador.daysMissing.asLiveData()

}