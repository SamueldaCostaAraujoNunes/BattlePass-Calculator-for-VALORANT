package br.com.battlepassCalculatorValorant.ui.fragment.Principal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Historic.Historic
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.ui.activity.Chart.ChartFactory
import br.com.battlepassCalculatorValorant.ui.activity.MainActivity
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView

class ChartExpPerTierFragment : Fragment() {

    private lateinit var properties: Properties
    private lateinit var historic: Historic
    override fun onCreate(savedInstanceState: Bundle?) {

        properties = MainActivity.Companion.properties
        historic = MainActivity.Companion.historic
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart_exp_per_tier, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val aaChartView = requireView().findViewById<AAChartView>(R.id.aa_chart_view)
        val chart = ChartFactory(requireContext(), aaChartView)
        chart.setData("XP Total") { chart.getTiersPerXp() }
        chart.setData("Seu Histórico") { properties.historicTierPositionPerXp() }
        chart.show()
        historic.add(chart)
    }

}