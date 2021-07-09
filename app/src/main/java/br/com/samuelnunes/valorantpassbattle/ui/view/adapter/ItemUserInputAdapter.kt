package br.com.samuelnunes.valorantpassbattle.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.samuelnunes.valorantpassbattle.databinding.ItemEditHistoricBinding
import br.com.samuelnunes.valorantpassbattle.model.dto.UserTier


class ItemUserInputAdapter :
    ListAdapter<UserTier, ItemUserInputAdapter.ViewHolder>(ItemUserInputAdapter) {

    private companion object : DiffUtil.ItemCallback<UserTier>() {
        override fun areItemsTheSame(oldItem: UserTier, newItem: UserTier): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserTier, newItem: UserTier): Boolean {
            return oldItem == newItem
        }
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
        holder.binding.userTier = getItem(position)
    }

}
