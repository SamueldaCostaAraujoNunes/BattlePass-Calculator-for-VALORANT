package br.com.battlepassCalculatorValorant.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Historic.Historic


class MyEditHistoricRecyclerViewAdapter(val context: Context, val historic: Historic) :
    RecyclerView.Adapter<MyEditHistoricRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTier: TextView = view.findViewById(R.id.tv_exp_initial_tier)
        val textViewExpMissing: TextView = view.findViewById(R.id.tv_exp_missing_tier)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_edit_historic, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvTier = holder.textViewTier
        val tvExpMissing = holder.textViewExpMissing
        val item = historic[position]
        tvTier.text = item.tierCurrent.toString()
        tvExpMissing.text = item.tierExpMissing.toString()
    }

    override fun getItemCount(): Int = historic.size
}