package br.com.battlepassCalculatorValorant.ui.view.fragment.Rewards

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.databinding.FragmentBaseRewardsBinding
import br.com.battlepassCalculatorValorant.model.battlePass.Reward
import br.com.battlepassCalculatorValorant.ui.view.adapter.ItemRewardAdapter

open class BaseRewardsFragment : Fragment() {
    protected lateinit var binding: FragmentBaseRewardsBinding
    protected lateinit var rewards: List<Reward>
    protected lateinit var positionTier: LiveData<Int>
    protected lateinit var adapter: ItemRewardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseRewardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(1)
        positionTier.observe(viewLifecycleOwner, Observer {
            updateRecyclerView(it)
        })
        binding.filter.setOnClickListener { search ->
            val ctw = ContextThemeWrapper(context, R.style.CustomPopupTheme)
            val menu = PopupMenu(ctw, search)

            val opcoes = Reward.types
            val opcoesTraduzidas = opcoes.map { Reward.getTypeTranslated(requireContext(), it) }

            for (type in opcoesTraduzidas) {
                menu.menu.add(type)
            }
            menu.show()
            menu.setOnMenuItemClickListener { item ->
                (binding.recycleView.adapter as ItemRewardAdapter).filter(
                    opcoes[opcoesTraduzidas.indexOf(
                        item.title.toString()
                    )]
                )
                true
            }
        }
    }

    private fun setupRecyclerView(position: Int) {
        with(binding.recycleView) {
            adapter = ItemRewardAdapter(rewards, position, requireActivity().supportFragmentManager)
            layoutManager?.scrollToPosition(position - 1)
        }
    }

    private fun updateRecyclerView(position: Int) {
        with(binding.recycleView) {
            (adapter as ItemRewardAdapter).rewardIndex = position
            layoutManager?.scrollToPosition(position - 1)
        }
    }
}