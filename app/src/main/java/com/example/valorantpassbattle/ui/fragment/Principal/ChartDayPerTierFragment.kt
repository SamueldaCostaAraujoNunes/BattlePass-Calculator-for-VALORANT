package com.example.valorantpassbattle.ui.fragment.Principal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.model.Historic.Historic
import com.example.valorantpassbattle.model.Properties.Properties
import com.example.valorantpassbattle.ui.activity.Chart.ChartFactory
import com.example.valorantpassbattle.ui.activity.MainActivity
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView

class ChartDayPerTierFragment : Fragment() {

    private lateinit var properties: Properties
    private lateinit var historic: Historic
    override fun onCreate(savedInstanceState: Bundle?) {

        properties = MainActivity.properties
        historic = MainActivity.historic
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chart_day_per_tier, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val aaChartView = requireView().findViewById<AAChartView>(R.id.aa_chart_view)
        val chart = ChartFactory(requireContext(), aaChartView)
        chart.setData("Tiers") { properties.historicTierPositionPerXp() }
        chart.show()
        historic.add(chart)
    }

}