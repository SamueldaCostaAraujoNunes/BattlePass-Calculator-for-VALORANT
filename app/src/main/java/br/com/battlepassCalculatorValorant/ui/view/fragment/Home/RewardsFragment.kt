package br.com.battlepassCalculatorValorant.ui.view.fragment.Home

import br.com.battlepassCalculatorValorant.ui.view.fragment.RewardsRecycler.ChapterRewards
import br.com.battlepassCalculatorValorant.ui.view.fragment.RewardsRecycler.TiersRewards


class RewardsFragment : PagerSliderFragment(listOf(TiersRewards(), ChapterRewards()))