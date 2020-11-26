package br.com.battlepassCalculatorValorant.ui.fragment.Principal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.ColorFromXml
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.ui.activity.MainActivity
import br.com.battlepassCalculatorValorant.ui.adapter.MyItemChapterRewardRecyclerViewAdapter


class ChaptersRewardsFragment : Fragment() {

    private var columnCount = 1
    private var chapterCurrent = 1
    lateinit var properties: Properties
    lateinit var colorXML: ColorFromXml

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        properties = MainActivity.properties
        colorXML = MainActivity.colorXML
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
        return view
    }
}