package br.com.battlepassCalculatorValorant.ui.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import br.com.battlepassCalculatorValorant.databinding.DialogTierinputBinding
import br.com.battlepassCalculatorValorant.model.FormInput
import br.com.battlepassCalculatorValorant.ui.viewModel.activity.UIViewModel
import br.com.battlepassCalculatorValorant.ui.viewModel.fragment.dialog.InputUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DialogInput : DialogBase() {

    companion object {
        const val TAG = "tag"
    }

    lateinit var binding: DialogTierinputBinding
    private val viewModel: InputUserViewModel by viewModels()
    private val uiViewModel: UIViewModel by activityViewModels()
//    private val notification = Notification(requireContext())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiViewModel.hideBottomNav()
    }

    override fun onDestroy() {
        super.onDestroy()
        uiViewModel.showBottomNav()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogTierinputBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        setOnClickListener()
        return binding.root
    }

    fun setOnClickListener() {
        binding.btnSave.setOnClickListener {
            val form = FormInput(
                binding.tietTierCurrent.text.toString(),
                binding.tietExpMissing.text.toString()
            )
            if (viewModel.validador(form)) {
                CoroutineScope(IO).launch {
                    viewModel.save(form)
                }
                dismiss()
//                notification.create()
            }
        }
        binding.btnCancel.setOnClickListener { dismiss() }
    }
}

