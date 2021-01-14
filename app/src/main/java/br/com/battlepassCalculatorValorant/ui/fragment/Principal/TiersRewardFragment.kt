package br.com.battlepassCalculatorValorant.ui.fragment.Principal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.BattlePass.Reward
import br.com.battlepassCalculatorValorant.model.ColorFromXml
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.model.SingletonPassBattle.ManagerColorFromXml
import br.com.battlepassCalculatorValorant.model.SingletonPassBattle.ManagerProperties
import br.com.battlepassCalculatorValorant.ui.adapter.MyItemTierRewardRecyclerViewAdapter


class TiersRewardFragment : Fragment() {

    private var columnCount = 1
    private var tierCurrent = 1
    lateinit var properties: Properties
    lateinit var colorXML: ColorFromXml

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        properties = ManagerProperties.getInstance(requireContext())
        colorXML = ManagerColorFromXml.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tiers_rewards, container, false)
        val rv = view.findViewById<RecyclerView>(R.id.recycleViewTierRewards)

        with(rv) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = MyItemTierRewardRecyclerViewAdapter(
                context = requireContext(),
                values = properties.getListTiers(),
                colorXML = colorXML,
                tierCurrentFun = properties::getTierCurrent
            )
            properties.historic.add(adapter as MyItemTierRewardRecyclerViewAdapter)
            tierCurrent = properties.getTierCurrent()
            layoutManager?.scrollToPosition(tierCurrent - 3)
        }


        val searchButton = view.findViewById<ImageButton>(R.id.btn_search_tier)

        searchButton.setOnClickListener { search ->
            val ctw = ContextThemeWrapper(context, R.style.CustomPopupTheme)
            val menu = PopupMenu(ctw, search)

            val opcoes = Reward.types
            val opcoesTraduzidas = opcoes.map { Reward.getTypeTranslated(requireContext(), it) }

            for (type in opcoesTraduzidas) {
                menu.menu.add(type)
            }
            menu.show()
            menu.setOnMenuItemClickListener { item ->
                (rv.adapter as MyItemTierRewardRecyclerViewAdapter).filter(
                    opcoes[opcoesTraduzidas.indexOf(
                        item.title.toString()
                    )]
                )
                true
            }
        }
        return view
    }
}