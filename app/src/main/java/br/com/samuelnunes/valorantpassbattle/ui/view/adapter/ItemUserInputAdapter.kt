package br.com.samuelnunes.valorantpassbattle.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.samuelnunes.valorantpassbattle.databinding.ItemEditHistoricBinding
import br.com.samuelnunes.valorantpassbattle.model.dto.UserTier


class ItemUserInputAdapter(val list: ArrayList<UserTier>) :
    RecyclerView.Adapter<ItemUserInputAdapter.ViewHolder>() {

    init {
        list.sortBy { it.tierCurrent }
    }

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
