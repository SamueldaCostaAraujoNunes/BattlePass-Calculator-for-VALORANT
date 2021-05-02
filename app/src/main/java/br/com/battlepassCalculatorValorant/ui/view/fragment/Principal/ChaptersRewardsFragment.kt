package br.com.battlepassCalculatorValorant.ui.view.fragment.Principal

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
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.model.Singleton.ManagerColorFromXml
import br.com.battlepassCalculatorValorant.model.Singleton.ManagerProperties
import br.com.battlepassCalculatorValorant.model.Util.ColorFromXml
import br.com.battlepassCalculatorValorant.ui.view.adapter.MyItemChapterRewardRecyclerViewAdapter


class ChaptersRewardsFragment : Fragment() {

    private var columnCount = 1
    private var chapterCurrent = 1
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
        val view = inflater.inflate(R.layout.fragment_chapters_rewards, container, false)

        val rv = view.findViewById<RecyclerView>(R.id.recycleViewChapterRewards)

        with(rv) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = MyItemChapterRewardRecyclerViewAdapter(
                context = requireContext(),
                chapters = properties.getListChapters(),
                colorXML = colorXML,
                chapterCurrentFun = properties::getChapterCurrent
            )
            properties.historic.add(adapter as MyItemChapterRewardRecyclerViewAdapter)
            chapterCurrent = properties.getChapterCurrent()
            layoutManager?.scrollToPosition(chapterCurrent - 1)
        }


        val searchButton = view.findViewById<ImageButton>(R.id.btn_search_chapter)
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
                (rv.adapter as MyItemChapterRewardRecyclerViewAdapter).filter(
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