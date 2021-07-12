package br.com.samuelnunes.valorantpassbattle.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.samuelnunes.valorantpassbattle.NavGraphDirections
import br.com.samuelnunes.valorantpassbattle.databinding.ItemRewardBinding
import br.com.samuelnunes.valorantpassbattle.model.dto.Reward


class ItemRewardAdapter(
    private var rewardCurrent: Int,
    private var origin: Int
) : ListAdapter<Reward, ItemRewardAdapter.ViewHolder>(ItemRewardAdapter) {

    private companion object : DiffUtil.ItemCallback<Reward>() {
        override fun areItemsTheSame(oldItem: Reward, newItem: Reward): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Reward, newItem: Reward): Boolean {
            return oldItem == newItem
        }
    }

    var rewardIndex: Int
        get() {
            return rewardCurrent
        }
        set(value) {
            rewardCurrent = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRewardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reward = getItem(position)
        holder.binding.reward = reward
        holder.binding.itemCurrent = rewardCurrent
        holder.itemView.setOnClickListener {
            it.findNavController()
                .navigate(NavGraphDirections.actionGlobalDialogReward(reward.id, origin))
        }
    }

    class ViewHolder(val binding: ItemRewardBinding) : RecyclerView.ViewHolder(binding.root)
}