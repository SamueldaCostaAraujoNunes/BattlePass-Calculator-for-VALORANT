package br.com.battlepassCalculatorValorant.ui.view.fragment.Infos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.databinding.FragmentTimelineBinding
import br.com.battlepassCalculatorValorant.ui.viewModel.fragment.infos.TimelineFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimelineFragment : Fragment() {
    private val viewModel: TimelineFragmentViewModel by viewModels()
    private lateinit var binding: FragmentTimelineBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimelineBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun toString(): String {
        return context?.getString(R.string.linha_do_tempo) ?: super.toString()
    }
}