package com.example.valorantpassbattle.ui.fragment.Principal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.ui.adapter.MyPageSliderAdapter
import com.example.valorantpassbattle.ui.fragment.Propriedades.ProgressFragment
import com.example.valorantpassbattle.ui.fragment.Propriedades.TimelineFragment
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

        adapter.addFragment(ProgressFragment())
        adapter.addFragment(TimelineFragment())
        pager.adapter = adapter
        dots_indicator.setViewPager2(pager)
    }
}

