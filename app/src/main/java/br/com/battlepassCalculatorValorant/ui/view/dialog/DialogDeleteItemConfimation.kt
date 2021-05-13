package br.com.battlepassCalculatorValorant.ui.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.database.room.model.UserTier
import br.com.battlepassCalculatorValorant.databinding.DialogConfirmationBinding
import br.com.battlepassCalculatorValorant.ui.viewModel.dialog.DialogDeleteItemConfimationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DialogDeleteItemConfimation(private val userInput: UserTier, val func: () -> Any) :
    DialogBase() {
    private val viewModel: DialogDeleteItemConfimationViewModel by viewModels()
    lateinit var binding: DialogConfirmationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogConfirmationBinding.inflate(inflater, container, false)
        binding.mensageContent.text = getString(R.string.text_delete, userInput.tierCurrent)
        setOnClickListener()
        return binding.root
    }


    fun setOnClickListener() {
        binding.btnSave.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.delete(userInput)
            }
            func()
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }


}