package br.com.battlepassCalculatorValorant.ui.view.fragment.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.battlepassCalculatorValorant.databinding.FragmentTierCurrentBinding
import br.com.battlepassCalculatorValorant.ui.viewModel.fragment.TierCurrentFragmentViewModel


class TierCurrentFragment : Fragment() {
    private val viewModel: TierCurrentFragmentViewModel by viewModels()
    private lateinit var binding: FragmentTierCurrentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTierCurrentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.indicator = binding.dotsIndicatorImages
        binding.lifecycleOwner = this
        return binding.root
    }
}