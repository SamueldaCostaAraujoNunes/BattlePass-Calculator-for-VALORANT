package br.com.battlepassCalculatorValorant.ui.view.fragment.Charts

import br.com.battlepassCalculatorValorant.R

class ChartExpPerDayFragment : ChartFragment() {
    override fun setData() {
        chart.setData(requireContext().getString(R.string.exp_esperado)) { properties.getExpectedExpPerDay() }
        chart.setData(requireContext().getString(R.string.exp_real)) { properties.getRealProgressPerDay() }
    }
}

class ChartExpPerTierFragment : ChartFragment() {
    override fun setData() {
        chart.setData(requireContext().getString(R.string.exp_total)) { properties.getTiersPerXp() }
        chart.setData(requireContext().getString(R.string.seu_historico)) { properties.historicTierPositionPerXp() }
    }
}