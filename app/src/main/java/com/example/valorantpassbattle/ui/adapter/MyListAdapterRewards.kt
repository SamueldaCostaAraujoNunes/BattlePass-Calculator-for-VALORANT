package com.example.valorantpassbattle.ui.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.valorantpassbattle.model.PassBattle.Tier
import com.example.valorantpassbattle.R


class MyListAdapterRewards(
        private val context: Activity,
        private val tiers: ArrayList<Tier>
        ) : ArrayAdapter<Tier>(context, R.layout.mylist, tiers) {

            override fun getView(position: Int, view: View?, parent: ViewGroup): View {
                val inflater = context.layoutInflater
                val rowView: View = inflater.inflate(R.layout.mylist, null, true)

                val indexText = rowView.findViewById(R.id.index) as TextView
                val expInitialText = rowView.findViewById(R.id.expInitial) as TextView
                val expMissingText = rowView.findViewById(R.id.expMissing) as TextView
                val totalPercentageText = rowView.findViewById(R.id.totalPercentage) as TextView
                val rewardText = rowView.findViewById(R.id.reward) as TextView

                indexText.text = tiers[position].index.toString()
                expInitialText.text = tiers[position].expInitial.toString()
                expMissingText.text = tiers[position].expMissing.toString()
                totalPercentageText.text = tiers[position].totalPercentage.toString()
                rewardText.text = tiers[position].reward

                return rowView
            }
}