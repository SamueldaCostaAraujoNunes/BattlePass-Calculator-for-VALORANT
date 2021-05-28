package br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Home

import br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Rewards.ChapterRewards
import br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Rewards.TiersRewards


class RewardsFragment : PagerSliderFragment(listOf(TiersRewards(), ChapterRewards()))