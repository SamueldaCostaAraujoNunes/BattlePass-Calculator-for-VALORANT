package br.com.battlepassCalculatorValorant.ui.view.fragment.RewardsRecycler

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.databinding.FragmentBaseRewardsBinding
import br.com.battlepassCalculatorValorant.model.battlePass.Reward
import br.com.battlepassCalculatorValorant.ui.view.adapter.ItemRewardAdapter

open class BaseRewardsFragment : Fragment() {
    lateinit var binding: FragmentBaseRewardsBinding
    protected lateinit var rewards: ArrayList<Reward>
    protected var positionCurrent: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseRewardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.recycleView) {
            adapter = ItemRewardAdapter(rewards, positionCurrent)
            layoutManager?.scrollToPosition(positionCurrent - 1)
        }

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
}