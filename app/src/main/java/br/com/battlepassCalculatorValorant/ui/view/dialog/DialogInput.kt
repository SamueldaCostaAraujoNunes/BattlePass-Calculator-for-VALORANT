package br.com.battlepassCalculatorValorant.ui.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import br.com.battlepassCalculatorValorant.databinding.DialogTierinputBinding
import br.com.battlepassCalculatorValorant.ui.viewModel.fragment.dialog.InputUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DialogInput : DialogFragment() {
    lateinit var binding: DialogTierinputBinding
    private val viewModel: InputUserViewModel by viewModels()
//    private val notification = Notification(requireContext())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
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

data class FormInput(val tierCurrent: String, val expMissing: String)

