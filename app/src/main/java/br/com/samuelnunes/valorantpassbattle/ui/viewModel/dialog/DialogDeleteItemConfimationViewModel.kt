package br.com.samuelnunes.valorantpassbattle.ui.viewModel.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.samuelnunes.valorantpassbattle.model.dto.UserTier
import br.com.samuelnunes.valorantpassbattle.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DialogDeleteItemConfimationViewModel @Inject constructor(private val calculador: CalculatorRepository) :
    ViewModel() {
    suspend fun delete(userTier: UserTier) = calculador.deleteUserInput(userTier)
    fun getUserTierById(userTierId: Int): LiveData<UserTier> =
        calculador.getUserTierById(userTierId).asLiveData()
}