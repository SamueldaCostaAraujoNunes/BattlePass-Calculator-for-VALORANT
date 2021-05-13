package br.com.battlepassCalculatorValorant.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.battlepassCalculatorValorant.database.room.model.UserTier
import br.com.battlepassCalculatorValorant.databinding.ItemEditHistoricBinding


class ItemUserInputAdapter(val list: List<UserTier>) :
    RecyclerView.Adapter<ItemUserInputAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemEditHistoricBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemEditHistoricBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.userTier = list[position]
    }

    override fun getItemCount(): Int = list.size
}