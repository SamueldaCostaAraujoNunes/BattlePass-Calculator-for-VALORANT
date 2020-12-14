package br.com.battlepassCalculatorValorant.ui.fragment.Principal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.model.SingletonPassBattle.ManagerProperties
import br.com.battlepassCalculatorValorant.ui.activity.Chart.Chart
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView

class ChartExpPerTierFragment : Fragment() {

    private lateinit var properties: Properties
    override fun onCreate(savedInstanceState: Bundle?) {
        properties = ManagerProperties.getInstance(requireContext())
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chart_exp_per_tier, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val aaChartView = requireView().findViewById<AAChartView>(R.id.aa_chart_view)
        val chart = Chart(requireContext(), aaChartView)
        chart.setData("XP Total") { properties.getTiersPerXp() }
        chart.setData("Seu Histórico") { properties.historicTierPositionPerXp() }
        chart.show()
        properties.historic.add(chart)
    }

}