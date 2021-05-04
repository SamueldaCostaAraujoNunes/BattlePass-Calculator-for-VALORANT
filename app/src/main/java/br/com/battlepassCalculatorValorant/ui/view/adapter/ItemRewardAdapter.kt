package br.com.battlepassCalculatorValorant.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.battlepassCalculatorValorant.databinding.ItemRewardBinding
import br.com.battlepassCalculatorValorant.model.battlePass.Reward
import br.com.battlepassCalculatorValorant.ui.view.dialog.DialogReward

class ItemRewardAdapter(private var rewards: ArrayList<Reward>, private var rewardCurrent: Int) :
    RecyclerView.Adapter<ItemRewardAdapter.ViewHolder>() {

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
            DialogReward(holder.itemView.context, reward).show()
        }
    }

    fun filter(text: String) {
        filterValues.clear()
        if (text == "Tudo") {
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