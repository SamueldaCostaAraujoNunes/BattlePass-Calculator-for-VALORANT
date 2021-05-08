package br.com.battlepassCalculatorValorant.ui.view.fragment.Home

import br.com.battlepassCalculatorValorant.ui.view.fragment.Rewards.ChapterRewards
import br.com.battlepassCalculatorValorant.ui.view.fragment.Rewards.TiersRewards


class RewardsFragment : PagerSliderFragment(listOf(TiersRewards(), ChapterRewards()))