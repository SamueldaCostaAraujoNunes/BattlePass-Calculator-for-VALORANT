package com.example.valorantpassbattle.ui.fragment.Principal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.ui.adapter.MyPageSliderAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_propriedades.*


class PropriedadesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_propriedades, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = MyPageSliderAdapter(this)

        adapter.addFragment(TierCurrentFragment(), "Tier Atual")
        adapter.addFragment(TiersFragment(), "Lista de Tiers")
        adapter.addFragment(ChartFragment(), "Grafico")
        pager.adapter = adapter
//        tabLayout.setupWithViewPager(pager)
        TabLayoutMediator(tabLayout, pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = adapter.getPageTitle(position)
            }).attach()
    }
}

