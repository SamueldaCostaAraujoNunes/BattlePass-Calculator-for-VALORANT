package br.com.battlepassCalculatorValorant.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.battlepassCalculatorValorant.NavGraphDirections
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.databinding.ItemRewardBinding
import br.com.battlepassCalculatorValorant.model.dto.Reward


class ItemRewardAdapter(
    private var rewards: List<Reward>,
    private var rewardCurrent: Int,
    private var origin: Int
) :
    RecyclerView.Adapter<ItemRewardAdapter.ViewHolder>() {

    private lateinit var all: String
    var rewardIndex: Int
        get() {
            return rewardCurrent
        }
        set(value) {
            rewardCurrent = value
            notifyDataSetChanged()
        }


    private var filterValues: ArrayList<Reward> = ArrayList(rewards)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        all = parent.context.getString(R.string.tudo)
        val binding = ItemRewardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reward = filterValues[position]
        holder.binding.reward = reward
        holder.binding.itemCurrent = rewardCurrent
        holder.itemView.setOnClickListener {
            it.findNavController()
                .navigate(NavGraphDirections.actionGlobalDialogReward(reward.id, origin))
        }
    }

    fun filter(text: String) {
        filterValues.clear()
        if (text == all) {
            filterValues.addAll(rewards)
        } else {
            for (item in rewards) {
                if (item.tipo == text) {
                    filterValues.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = filterValues.size

    class ViewHolder(val binding: ItemRewardBinding) : RecyclerView.ViewHolder(binding.root)
}