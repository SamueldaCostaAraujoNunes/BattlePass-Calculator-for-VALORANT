package br.com.battlepassCalculatorValorant.ui.viewModel.fragment.charts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.battlepassCalculatorValorant.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChartFragmentViewModel @Inject constructor(calculador: CalculatorRepository) :
    ViewModel() {

    val tiersPerExp: ArrayList<Int> = calculador.tiersPerExp
    val listOfTiersCompletedByTheUser: LiveData<List<Int>> =
        calculador.listOfTiersCompletedByTheUser.asLiveData()

    val expExpectedPerDay: ArrayList<Int> = calculador.expExpectedPerDay
    val projectionOfProgressPerDay: LiveData<List<Int>> =
        calculador.projectionOfProgressPerDay.asLiveData()


}