package br.com.samuelnunes.valorantpassbattle.ui.viewModel.dialog

import androidx.lifecycle.ViewModel
import br.com.samuelnunes.valorantpassbattle.model.dto.FormQuestion
import br.com.samuelnunes.valorantpassbattle.repository.FormsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DialogFormReportBugsViewModel @Inject constructor(
    private val repository: FormsRepository
) : ViewModel() {
    suspend fun submitAnswers(answers: List<FormQuestion>) {
        repository.submitAnswers(answers)
    }

    fun getIdBattlePass(): String {
        return repository.getIdBattlePass()
    }
}