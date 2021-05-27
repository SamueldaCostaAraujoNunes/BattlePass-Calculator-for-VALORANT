package br.com.battlepassCalculatorValorant.ui.viewModel.fragment.infos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.battlepassCalculatorValorant.model.GameType.*
import br.com.battlepassCalculatorValorant.model.dto.PrevisoesJogos
import br.com.battlepassCalculatorValorant.repository.CalculatorRepository
import br.com.battlepassCalculatorValorant.ui.view.fragment.Infos.ProjectionsFragment.Companion.DISPARADA
import br.com.battlepassCalculatorValorant.ui.view.fragment.Infos.ProjectionsFragment.Companion.DISPUTA_DA_SPIKE
import br.com.battlepassCalculatorValorant.ui.view.fragment.Infos.ProjectionsFragment.Companion.MATA_MATA
import br.com.battlepassCalculatorValorant.ui.view.fragment.Infos.ProjectionsFragment.Companion.SEM_CLASSIFICACAO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectionsFragmentViewModel @Inject constructor(val calculator: CalculatorRepository) :
    ViewModel() {

    private fun factoryGameType(type: Int): GameType {
        return when (type) {
            DISPUTA_DA_SPIKE -> DisputaDeSpike()
            SEM_CLASSIFICACAO -> SemClassificacao()
            DISPARADA -> Disparada()
            MATA_MATA -> MataMata()
            else -> DisputaDeSpike()
        }
    }

    fun previsoesDosJogos(type: Int): LiveData<PrevisoesJogos> =
        calculator.previsoesJogos(factoryGameType(type)).asLiveData()
}
