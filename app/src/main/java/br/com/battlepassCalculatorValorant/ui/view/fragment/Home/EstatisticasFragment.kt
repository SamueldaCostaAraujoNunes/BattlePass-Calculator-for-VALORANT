package br.com.battlepassCalculatorValorant.ui.view.fragment.Home

import br.com.battlepassCalculatorValorant.ui.view.fragment.Infos.ProgressFragment
import br.com.battlepassCalculatorValorant.ui.view.fragment.Infos.ProjectionsFragment
import br.com.battlepassCalculatorValorant.ui.view.fragment.Infos.TimelineFragment


class EstatisticasFragment :
    PagerSliderFragment(listOf(ProgressFragment(), TimelineFragment(), ProjectionsFragment()))

