package br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Charts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.samuelnunes.valorantpassbattle.ui.view.viewsCustom.Chart
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.fragment.charts.ChartFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class ChartFragment : Fragment() {
    protected val viewModel: ChartFragmentViewModel by viewModels()
    private lateinit var chart: Chart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return Chart(requireContext()).also { chart = it }
    }

    fun setData(label: String, data: List<Int>) {
        chart.setData(label, data)
        chart.show()
    }
}

