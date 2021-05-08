package br.com.battlepassCalculatorValorant.ui.view.fragment.Charts

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import br.com.battlepassCalculatorValorant.R

class ChartExpPerDayFragment : ChartFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setData(requireContext().getString(R.string.exp_esperado), viewModel.expExpectedPerDay)
        viewModel.projectionOfProgressPerDay.observe(viewLifecycleOwner, Observer {
            setData(requireContext().getString(R.string.exp_real), it)
        })
    }
}

class ChartExpPerTierFragment : ChartFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setData(requireContext().getString(R.string.exp_total), viewModel.tiersPerExp)
        viewModel.listOfTiersCompletedByTheUser.observe(viewLifecycleOwner, Observer {
            setData(requireContext().getString(R.string.seu_historico), it)
        })
    }
}