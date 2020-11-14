package com.example.valorantpassbattle.ui.fragment.Principal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.model.Observer.IObserver
import com.example.valorantpassbattle.model.Properties.Properties
import com.example.valorantpassbattle.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_tier_current.*

class TierCurrentFragment : Fragment(), IObserver {

    private lateinit var properties: Properties

    override fun onCreate(savedInstanceState: Bundle?) {
        properties = MainActivity.Companion.properties
        properties.historic.add(this)
        super.onCreate(savedInstanceState)
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
            val tierReward = tier.reward
            val percentageTier = properties.percentageTier()
            val percentageTierText = percentageTier.toString() + "% Feito"

            tv_tier_index.text = tierIndex
            tv_tier_rewind.text = tierReward
            tv_percentage_tier.text = percentageTierText
        }
    }
}