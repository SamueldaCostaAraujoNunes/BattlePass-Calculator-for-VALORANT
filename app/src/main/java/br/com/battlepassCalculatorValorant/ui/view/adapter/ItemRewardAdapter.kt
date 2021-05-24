package br.com.battlepassCalculatorValorant.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.battlepassCalculatorValorant.NavGraphDirections
import br.com.battlepassCalculatorValorant.databinding.ItemRewardBinding
import br.com.battlepassCalculatorValorant.model.battlePass.Reward


class ItemRewardAdapter(
    private var rewards: List<Reward>,
    private var rewardCurrent: Int,
    private val fragmentManager: FragmentManager,
    private val all: String
) :
    RecyclerView.Adapter<ItemRewardAdapter.ViewHolder>() {

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
        val binding = ItemRewardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reward = filterValues[position]
        holder.binding.reward = reward
        holder.binding.itemCurrent = rewardCurrent
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(NavGraphDirections.actionGlobalDialogReward(reward.id))
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