package br.com.battlepassCalculatorValorant.ui.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import br.com.battlepassCalculatorValorant.databinding.DialogChooseThemesBinding
import br.com.battlepassCalculatorValorant.ui.viewModel.activity.UIViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogChooseThemes : DialogBase() {
    lateinit var binding: DialogChooseThemesBinding
    private val uiViewModel: UIViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogChooseThemesBinding.inflate(inflater, container, false)
        binding.viewModel = uiViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}