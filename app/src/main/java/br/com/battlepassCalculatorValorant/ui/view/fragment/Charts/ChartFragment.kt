package br.com.battlepassCalculatorValorant.ui.view.fragment.Charts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.model.Singleton.ManagerProperties
import br.com.battlepassCalculatorValorant.ui.view.viewsCustom.Chart

abstract class ChartFragment : Fragment() {
    lateinit var chart: Chart
    lateinit var properties: Properties
    override fun onCreate(savedInstanceState: Bundle?) {
        properties = ManagerProperties.getInstance(requireContext())
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        chart = Chart(requireContext())
        setData()
        properties.historic.add(chart)
        return chart
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        chart.show()
    }

    abstract fun setData()
}