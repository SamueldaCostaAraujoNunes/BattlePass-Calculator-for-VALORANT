package br.com.battlepassCalculatorValorant.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.BattlePass.Chapter
import br.com.battlepassCalculatorValorant.model.BattlePass.Reward
import br.com.battlepassCalculatorValorant.model.Observer.IObserver
import br.com.battlepassCalculatorValorant.model.Util.ColorFromXml
import br.com.battlepassCalculatorValorant.ui.dialog.DialogChapter

class MyItemChapterRewardRecyclerViewAdapter(
    private val context: Context,
    private var chapters: ArrayList<Chapter>,
    private val chapterCurrentFun: () -> Int,
    private val colorXML: ColorFromXml
) : RecyclerView.Adapter<MyItemChapterRewardRecyclerViewAdapter.ViewHolder>(), IObserver {
    private var chapterCurrent = 0
    private var values: ArrayList<RewardWithParent> = ArrayList()
    private var filterValues: ArrayList<RewardWithParent> = ArrayList()

    init {
        for (chapter in chapters) {
            for (reward in chapter.reward) {
                values.add(RewardWithParent(reward, chapter.index))
            }
        }
        filterValues.addAll(values)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        chapterCurrent = chapterCurrentFun()
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_tiers, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filterValues[position]
        val chapterIndexView = holder.idView
        val chapterRewardView = holder.contentView

        val gray = Color.parseColor(colorXML.getColor(R.attr.colorOnSecondary))
        val white = Color.parseColor(colorXML.getColor(R.attr.colorOnPrimary))
        val accent = Color.parseColor(colorXML.getColor(R.attr.colorAccent))

        chapterIndexView.setTextColor(white)
        chapterRewardView.setTextColor(white)
        chapterRewardView.paintFlags =
            chapterRewardView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

        if (item.index < chapterCurrent) {
            chapterRewardView.paintFlags =
                chapterRewardView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            chapterIndexView.setTextColor(gray)
            chapterRewardView.setTextColor(gray)
        } else if (item.index == chapterCurrent) {
            chapterIndexView.setTextColor(accent)
            chapterRewardView.setTextColor(accent)
        }

        val contentIndex = "C" + item.index
        val contentReward = item.reward.getTypeTranslated(context) + " " + item.reward.nome
        chapterIndexView.text = contentIndex
        chapterRewardView.text = contentReward

        holder.itemView.setOnClickListener {
            DialogChapter(context, item).show()
        }
    }

    fun filter(text: String) {
        filterValues.clear()
        if (text == "Tudo") {
            filterValues.addAll(values)
        } else {
            for (item in values) {
                if (item.reward.tipo == text) {
                    filterValues.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = filterValues.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)
    }

    override fun update() {
        chapterCurrent = chapterCurrentFun()
        notifyDataSetChanged()
    }
}

class RewardWithParent(val reward: Reward, val index: Int)