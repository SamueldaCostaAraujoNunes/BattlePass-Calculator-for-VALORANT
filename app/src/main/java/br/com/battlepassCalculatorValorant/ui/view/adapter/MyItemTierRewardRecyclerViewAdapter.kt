package br.com.battlepassCalculatorValorant.ui.view.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.BattlePass.Tier
import br.com.battlepassCalculatorValorant.model.Observer.IObserver
import br.com.battlepassCalculatorValorant.model.Singleton.ManagerColorFromXml
import br.com.battlepassCalculatorValorant.ui.view.dialog.DialogTier


class MyItemTierRewardRecyclerViewAdapter(
    private val context: Context,
    private val values: ArrayList<Tier>,
    private val tierCurrentFun: () -> Int
) : RecyclerView.Adapter<MyItemTierRewardRecyclerViewAdapter.ViewHolder>(), IObserver {
    private var tierCurrent = 0
    private val filterValues = values.toMutableList()
    private var colorXML = ManagerColorFromXml.getInstance(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        tierCurrent = tierCurrentFun()
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_tiers, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filterValues[position]
        val tierIndexView = holder.idView
        val tierRewardView = holder.contentView

        val gray = Color.parseColor(colorXML.getColor(R.attr.colorOnSecondary))
        val white = Color.parseColor(colorXML.getColor(R.attr.colorOnPrimary))
        val accent = Color.parseColor(colorXML.getColor(R.attr.colorAccent))

        tierRewardView.paintFlags = tierRewardView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

        when {
            item.index < tierCurrent -> {
                tierRewardView.paintFlags = tierRewardView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                tierIndexView.setTextColor(gray)
                tierRewardView.setTextColor(gray)
            }
            item.index == tierCurrent -> {
                tierIndexView.setTextColor(accent)
                tierRewardView.setTextColor(accent)
            }
            else -> {
                tierIndexView.setTextColor(white)
                tierRewardView.setTextColor(white)
            }
        }

        val markerTier = if (position < 50) "T" else "E"
        val idTier = markerTier + item.index.toString()
        tierIndexView.text = idTier
        val reward = item.reward[0].getTypeTranslated(context) + " " + item.reward[0].nome
        tierRewardView.text = reward

        holder.itemView.setOnClickListener {
            DialogTier(context, item).show()
        }
    }

    fun filter(text: String) {
        filterValues.clear()
        if (text == "Tudo") {
            filterValues.addAll(values)
        } else {
            for (item in values) {
                if (item.reward[0].tipo == text) {
                    filterValues.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = filterValues.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)
    }

    override fun update() {
        tierCurrent = tierCurrentFun()
        notifyDataSetChanged()
    }
}