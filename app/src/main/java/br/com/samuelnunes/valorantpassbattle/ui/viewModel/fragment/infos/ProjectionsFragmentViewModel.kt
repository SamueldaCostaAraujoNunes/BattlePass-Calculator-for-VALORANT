package br.com.samuelnunes.valorantpassbattle.ui.viewModel.fragment.infos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.samuelnunes.valorantpassbattle.model.GameType.*
import br.com.samuelnunes.valorantpassbattle.model.dto.PrevisoesJogos
import br.com.samuelnunes.valorantpassbattle.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectionsFragmentViewModel @Inject constructor(val calculator: CalculatorRepository) :
    ViewModel() {

    fun previsoesDosJogos(gameType: GameType): LiveData<PrevisoesJogos> =
        calculator.previsoesJogos(gameType).asLiveData()
}
