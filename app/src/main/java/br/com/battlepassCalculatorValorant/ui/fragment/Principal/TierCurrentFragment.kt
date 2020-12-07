package br.com.battlepassCalculatorValorant.ui.fragment.Principal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Observer.IObserver
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.ui.activity.MainActivity
import br.com.battlepassCalculatorValorant.ui.adapter.MyImageSliderAdapter
import kotlinx.android.synthetic.main.fragment_tier_current.*


class TierCurrentFragment : Fragment(), IObserver {

    private lateinit var properties: Properties

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        properties = MainActivity.Companion.properties
        properties.historic.add(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tier_current, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        update()
    }

    override fun update() {
        val lastTierIndex =
            if (properties.historic.isEmpty()) 1 else properties.historic.last().tierCurrent
        val tier = properties.passBattle.getTier(lastTierIndex)
        if (tier != null) {
            val tierIndex = "Tier " + tier.index.toString()
            val tierReward = tier.reward[0].nome
            val percentageTier = properties.percentageTier()
            val percentageTierText = percentageTier.toString() + "% Feito"

            tv_tier_index.text = tierIndex
            tv_tier_rewind.text = tierReward
            tv_percentage_tier.text = percentageTierText

            val mViewPagerAdapter = MyImageSliderAdapter(requireContext(), tier.reward[0])
            viewPagerMain.adapter = mViewPagerAdapter
            dots_indicator_images.setViewPager2(viewPagerMain)
        }
    }

}