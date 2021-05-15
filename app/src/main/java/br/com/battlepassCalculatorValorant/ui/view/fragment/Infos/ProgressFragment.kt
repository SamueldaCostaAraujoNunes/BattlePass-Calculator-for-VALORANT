package br.com.battlepassCalculatorValorant.ui.view.fragment.Infos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.databinding.FragmentProgressBinding
import br.com.battlepassCalculatorValorant.ui.viewModel.fragment.infos.ProgressFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProgressFragment : Fragment() {
    private val viewModel: ProgressFragmentViewModel by viewModels()
    private lateinit var binding: FragmentProgressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }

    override fun toString(): String {
        return context?.getString(R.string.progresso) ?: super.toString()
    }
}