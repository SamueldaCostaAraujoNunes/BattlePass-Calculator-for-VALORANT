package br.com.battlepassCalculatorValorant.ui.viewModel.dialog

import androidx.lifecycle.ViewModel
import br.com.battlepassCalculatorValorant.database.room.model.UserTier
import br.com.battlepassCalculatorValorant.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DialogDeleteItemConfimationViewModel @Inject constructor(val calculador: CalculatorRepository) :
    ViewModel() {
    suspend fun delete(userTier: UserTier) = calculador.deleteUserInput(userTier)
}