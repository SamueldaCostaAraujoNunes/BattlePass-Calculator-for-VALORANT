package br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Charts

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import br.com.samuelnunes.valorantpassbattle.R

class ChartExpPerDayFragment : ChartFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.projectionOfProgressPerDay.observe(viewLifecycleOwner, Observer {
            setData(requireContext().getString(R.string.exp_real), it)
        })
        viewModel.expExpectedPerDay.observe(viewLifecycleOwner, Observer {
            setData(requireContext().getString(R.string.exp_esperado), it)
        })
    }
}

class ChartExpPerTierFragment : ChartFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.listOfTiersCompletedByTheUser.observe(viewLifecycleOwner, Observer {
            setData(requireContext().getString(R.string.seu_historico), it)
        })
        setData(requireContext().getString(R.string.exp_total), viewModel.tiersPerExp)
    }
}
