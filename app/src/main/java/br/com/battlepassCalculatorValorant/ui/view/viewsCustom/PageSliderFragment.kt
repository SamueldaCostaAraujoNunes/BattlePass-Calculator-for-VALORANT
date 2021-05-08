package br.com.battlepassCalculatorValorant.ui.view.fragment.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.databinding.FragmentPagerSliderBinding
import br.com.battlepassCalculatorValorant.extensions.bindingAdapters.setAdapterSlider
import br.com.battlepassCalculatorValorant.ui.view.adapter.FragmentSliderAdapter

abstract class PagerSliderFragment(private var fragments: List<Fragment> = ArrayList()) :
    Fragment() {
    private lateinit var binding: FragmentPagerSliderBinding
    private lateinit var adapter: FragmentSliderAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPagerSliderBinding.inflate(inflater, container, false)
        adapter = FragmentSliderAdapter(this)
        adapter.addFragments(fragments)
        binding.dotsIndicator.dotsClickable = false
        binding.pager.setAdapterSlider(adapter)
        binding.dotsIndicator.setViewPager2(binding.pager)
        binding.lifecycleOwner = this
        return binding.root
    }
}