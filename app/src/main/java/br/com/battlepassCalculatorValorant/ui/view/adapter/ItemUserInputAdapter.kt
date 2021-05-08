package br.com.battlepassCalculatorValorant.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.battlepassCalculatorValorant.databinding.ItemEditHistoricBinding
import br.com.battlepassCalculatorValorant.extensions.bindingAdapters.fadeAnimationText
import br.com.battlepassCalculatorValorant.model.Historic.Historic


class ItemUserInputAdapter(val context: Context, val historic: Historic) :
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
        val item = historic[position]
        holder.binding.tvExpInitialTier.fadeAnimationText(item.tierCurrent.toString())
        holder.binding.tvExpMissingTier.fadeAnimationText(item.tierExpMissing.toString())
    }

    override fun getItemCount(): Int = historic.size
}