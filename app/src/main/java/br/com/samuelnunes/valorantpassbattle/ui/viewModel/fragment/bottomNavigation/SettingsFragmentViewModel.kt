package br.com.samuelnunes.valorantpassbattle.ui.viewModel.fragment.bottomNavigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.samuelnunes.valorantpassbattle.model.dto.UserTier
import br.com.samuelnunes.valorantpassbattle.repository.CalculatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SettingsFragmentViewModel @Inject constructor(val calculador: CalculatorRepository) :
    ViewModel() {

    val lastTier: LiveData<UserTier> = calculador.lastUserInput.asLiveData()
}
