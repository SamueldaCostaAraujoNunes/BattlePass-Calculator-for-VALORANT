package br.com.battlepassCalculatorValorant.ui.view.fragment.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.databinding.FragmentEstatisticasBinding
import br.com.battlepassCalculatorValorant.extensions.bindingAdapters.setAdapterSlider
import br.com.battlepassCalculatorValorant.ui.view.adapter.MyPageSliderAdapter
import br.com.battlepassCalculatorValorant.ui.view.fragment.Infos.ProgressFragment
import br.com.battlepassCalculatorValorant.ui.view.fragment.Infos.ProjectionsFragment
import br.com.battlepassCalculatorValorant.ui.view.fragment.Infos.TimelineFragment


class EstatisticasFragment : Fragment() {
    private lateinit var binding: FragmentEstatisticasBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEstatisticasBinding.inflate(inflater, container, false)
        val adapter = MyPageSliderAdapter(this)

        adapter.addFragment(ProgressFragment())
        adapter.addFragment(TimelineFragment())
        adapter.addFragment(ProjectionsFragment())

        binding.dotsIndicator.dotsClickable = false
        binding.pager.setAdapterSlider(adapter)
        binding.dotsIndicator.setViewPager2(binding.pager)
        binding.lifecycleOwner = this

        return binding.root
    }
}

