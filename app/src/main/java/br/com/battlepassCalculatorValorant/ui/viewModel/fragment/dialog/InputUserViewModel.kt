package br.com.battlepassCalculatorValorant.ui.viewModel.fragment.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.database.room.model.UserTier
import br.com.battlepassCalculatorValorant.model.FormInput
import br.com.battlepassCalculatorValorant.repository.CalculatorRepository
import br.com.battlepassCalculatorValorant.util.ViewModelString
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InputUserViewModel @Inject constructor(private val calculador: CalculatorRepository) :
    ViewModel() {

    private val last = calculador.lastUserInput
    fun getUserTierById(userTierId: Int): LiveData<UserTier> =
        calculador.getUserTierById(userTierId).asLiveData()

    val fieldTierError = MutableLiveData<ViewModelString?>()
    val fieldExpMissing = MutableLiveData<ViewModelString?>()

    // Validador de Entradas
    fun validador(form: FormInput): Boolean {
        val validadorTier: Boolean = validadorTier(form.tierCurrent)
        val validadorExpMissing: Boolean = validadorExpMissing(form, validadorTier)
        return (validadorTier && validadorExpMissing)
    }

    private fun validadorExpMissing(form: FormInput, validadorTier: Boolean): Boolean {
        var valido = true
        var error: ViewModelString? = null
        if (validadorTier) {
            if (form.expMissing.isEmpty()) {
                valido = false
                error = ViewModelString(R.string.insira_o_exp_faltando)
            } else {
                val expMin = 0
                val expMax =
                    calculador.battlePassManager.expParaCompletarTier(form.tierCurrent.toInt())
                val expMissingInt = form.expMissing.toInt()

                if ((expMissingInt < expMin) or (expMissingInt > expMax)) {
                    valido = false
                    error = ViewModelString(R.string.exp_intervalo, arrayListOf(expMin, expMax))
                }
            }
        }
        fieldExpMissing.value = error
        return valido
    }


    private fun validadorTier(tierCurrent: String): Boolean {
        var valido = true
        var error: ViewModelString? = null
        if (tierCurrent.isEmpty()) {
            valido = false
            error = ViewModelString(R.string.insira_um_tier)
        } else {
            val tierInt = tierCurrent.toInt()
            val tierMin = 1
            val tierMax = 55
            if ((tierInt < tierMin) or (tierInt > tierMax)) {
                valido = false
                error = ViewModelString(R.string.tier_intervalo, arrayListOf(tierMin, tierMax))
            }
        }
        fieldTierError.value = error
        return valido
    }

    suspend fun save(userTier: UserTier) {
        Timber.i("Salvando: ${userTier}")
        calculador.insertUserInput(userTier)
    }


}