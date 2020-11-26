package br.com.battlepassCalculatorValorant.ui.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.ui.adapter.MyImageSliderAdapter
import br.com.battlepassCalculatorValorant.ui.adapter.RewardWithParent
import kotlinx.android.synthetic.main.dialog_chapter.view.*
import kotlinx.android.synthetic.main.dialog_tier.view.dialogViewPagerMain
import kotlinx.android.synthetic.main.dialog_tier.view.indicator_image_slider
import kotlinx.android.synthetic.main.dialog_title.view.*


@SuppressLint("InflateParams")
class DialogChapter(context: Context, chapter: RewardWithParent) : AlertDialog(context) {
    var builder: Builder

    init {
        builder = Builder(context)
            .setView(createContent(chapter))
            .setCustomTitle(createTitle("Capítulo ${chapter.index}"))
    }

    private fun createTitle(title: String): View {
        val titleView: View = this.layoutInflater.inflate(R.layout.dialog_title, null)
        titleView.title.text = title
        return titleView
    }

    private fun createContent(chapter: RewardWithParent): View {
        val mDialogView = this.layoutInflater.inflate(R.layout.dialog_chapter, null)
        with(mDialogView) {
            val tipo = chapter.reward.tipo
            val reward = chapter.reward

            tv_reward_chapter.text = reward.nome
            tv_type_chapter.text = tipo

            val mViewPagerAdapter = MyImageSliderAdapter(context, chapter.reward)
            dialogViewPagerMain.adapter = mViewPagerAdapter
            indicator_image_slider.setViewPager2(dialogViewPagerMain)
        }
        return mDialogView
    }

    override fun show() {
        builder.show()
    }

}