package br.com.battlepassCalculatorValorant.ui.view.fragment.Principal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.ui.view.adapter.MyPageSliderAdapter
import kotlinx.android.synthetic.main.fragment_rewards.*


class RewardsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rewards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = MyPageSliderAdapter(this)

        adapter.addFragment(TiersRewardFragment())
        adapter.addFragment(ChaptersRewardsFragment())

        pager_rewards.adapter = adapter

        dots_indicator_rewards.setViewPager2(pager_rewards)
        dots_indicator_rewards.dotsClickable = false

    }

}

