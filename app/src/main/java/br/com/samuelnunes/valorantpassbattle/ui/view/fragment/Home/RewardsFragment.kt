package br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Home

import br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Rewards.ChapterRewards
import br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Rewards.TiersRewards
import br.com.samuelnunes.valorantpassbattle.ui.view.viewsCustom.PagerSliderFragment


class RewardsFragment : PagerSliderFragment(listOf(TiersRewards(), ChapterRewards()), false)