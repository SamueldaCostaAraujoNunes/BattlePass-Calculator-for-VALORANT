package com.example.valorantpassbattle.ui.adapter

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.model.ColorFromXml
import com.example.valorantpassbattle.model.Observer.IObserver
import com.example.valorantpassbattle.model.PassBattle.Tier


class MyItemRecyclerViewAdapter(
    private val values: ArrayList<Tier>,
    private val tierCurrentFun: () -> Int,
    private val colorXML: ColorFromXml
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>(), IObserver {
    private var tierCurrent = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        tierCurrent = tierCurrentFun()
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_tiers, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val tierIndexView = holder.idView
        val tierRewardView = holder.contentView

        val gray = Color.parseColor(colorXML.getColorFromColors(R.color.mGray))
        val white = Color.parseColor(colorXML.getColorFromColors(R.color.text_light))
        val accent = Color.parseColor(colorXML.getColor(R.attr.colorAccent))

        tierIndexView.setTextColor(white)
        tierRewardView.setTextColor(white)
        tierRewardView.paintFlags = tierRewardView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

        if(item.index < tierCurrent) {
            tierRewardView.paintFlags = tierRewardView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            tierIndexView.setTextColor(gray)
            tierRewardView.setTextColor(gray)
        }
        else if(item.index == tierCurrent){
            tierIndexView.setTextColor(accent)
            tierRewardView.setTextColor(accent)
        }

        tierIndexView.text = item.index.toString()
        tierRewardView.text = item.reward
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)
    }

    override fun update() {
        tierCurrent = tierCurrentFun()
        notifyDataSetChanged()
    }
}