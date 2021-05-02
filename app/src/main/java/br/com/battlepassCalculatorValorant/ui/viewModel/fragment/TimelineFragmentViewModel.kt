package br.com.battlepassCalculatorValorant.ui.viewModel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimelineFragmentViewModel : ViewModel() {

    private val _dataInicioAto: MutableLiveData<String> = MutableLiveData<String>("Teste")
    private val _dataFinalizacao: MutableLiveData<String> = MutableLiveData<String>("Teste")
    private val _dataFinalAto: MutableLiveData<String> = MutableLiveData<String>("Teste")
    private val _diaAtualDoPasse: MutableLiveData<Int> = MutableLiveData<Int>(0)
    private val _diasParaOFimDoPasse: MutableLiveData<Int> = MutableLiveData<Int>(1)
    private val _diasAdiantadoAtrasado: MutableLiveData<Int> = MutableLiveData<Int>(2)


    val dataInicioAto: LiveData<String> = _dataInicioAto
    val dataFinalizacao: LiveData<String> = _dataFinalizacao
    val dataFinalAto: LiveData<String> = _dataFinalAto
    val diaAtualDoPasse: LiveData<Int> = _diaAtualDoPasse
    val diasParaOFimDoPasse: LiveData<Int> = _diasParaOFimDoPasse
    val diasAdiantadoAtrasado: LiveData<Int> = _diasAdiantadoAtrasado

//    val dateInit = properties.passBattle.getDateInit()
//    val dateFinally = properties.passBattle.getDateFinally()
//    val finishForecast = properties.finishForecast()
//    val daysMissing = properties.daysMissing()!!
//    val dayCurrent = properties.dayCurrent()
//    val daysForClosed = properties.daysForClosed()

}