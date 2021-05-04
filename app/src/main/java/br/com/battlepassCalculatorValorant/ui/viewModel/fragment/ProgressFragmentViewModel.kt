package br.com.battlepassCalculatorValorant.ui.viewModel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProgressFragmentViewModel : ViewModel() {

//    init {
//        viewModelScope.launch {
//            withContext(IO) {
//                while (true) {
//                    delay(2000)
//                    _expAdiantadoAtrasado.postValue(_expAdiantadoAtrasado.value?.plus(1))
//                    Timber.i("Counter", _expAdiantadoAtrasado.value.toString())
//                }
//            }
//        }
//    }

    private val _totalExp: MutableLiveData<Int> = MutableLiveData<Int>(0)
    private val _porcentagemProgresso: MutableLiveData<Float> = MutableLiveData<Float>(0F)
    private val _expPerDay: MutableLiveData<Int> = MutableLiveData<Int>(0)
    private val _expAdiantadoAtrasado: MutableLiveData<Int> = MutableLiveData<Int>(-5)


    val totalExp: LiveData<Int> = _totalExp
    val porcentagemProgresso: LiveData<Float> = _porcentagemProgresso
    val expPerDay: LiveData<Int> = _expPerDay
    val expAdiantadoAtrasado: LiveData<Int> = _expAdiantadoAtrasado

//        val totalDeXp = properties.getTotalXp().toString() + " EXP"
//        val progressPorcent = properties.getProgressPorcent().toString() + "%"
//        val xpPerDay = properties.getXpPerDia().toString() + " EXP"
//        val expAdiantadoAtrasado = properties.getExpAdiantAtrasado()

}