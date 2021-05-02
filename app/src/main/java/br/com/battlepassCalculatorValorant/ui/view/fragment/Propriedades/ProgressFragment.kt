package br.com.battlepassCalculatorValorant.ui.view.fragment.Propriedades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.battlepassCalculatorValorant.databinding.FragmentProgressBinding
import br.com.battlepassCalculatorValorant.ui.viewModel.fragment.ProgressFragmentViewModel

class ProgressFragment : Fragment() {
    private val viewModel: ProgressFragmentViewModel by viewModels()
    private lateinit var binding: FragmentProgressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProgressBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}