package br.com.battlepassCalculatorValorant.ui.progressBar

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import br.com.battlepassCalculatorValorant.R

class mProgressBarView : ConstraintLayout {
    private lateinit var view: View
    private lateinit var progressBar: ProgressBar
    private lateinit var titleTextView: TextView
    private lateinit var contentTextView: TextView
    private lateinit var percentageTextView: TextView

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.view_m_progress_bar, this, true)
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        progressBar = view.findViewById(R.id.progressBar)
        titleTextView = view.findViewById(R.id.tv_title_progress)
        contentTextView = view.findViewById(R.id.tv_progress)
        percentageTextView = view.findViewById(R.id.tv_percentage_in_battle_pass)
        contentUpdate()
    }

    fun setupProgress(
        pTitle: String,
        pProgress: Int,
        pProgressMax: Int,
        widthPercentage: Float,
        color: Int
    ) {
        title = pTitle
        progressMax = pProgressMax
        progress = pProgress
        setPercentage(widthPercentage)
        setWidthPercent(widthPercentage)
        setColor(color)
    }

    fun setColor(color: Int) {
        progressBar.progressDrawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)
    }

    fun setPercentage(value: Float) {
        val percentage = "${(value * 100).toInt()}%"
        percentageTextView.text = percentage
    }

    var title: String
        get() = titleTextView.text.toString()
        set(value) {
            titleTextView.text = value
        }

    var progress: Int
        get() = progressBar.progress
        set(value) {
            progressBar.progress = value
            contentUpdate()
        }

    var progressMax: Int
        get() = progressBar.max
        set(value) {
            progressBar.max = value
            contentUpdate()
        }

    fun setWidthPercent(value: Float) {
        val set = ConstraintSet()
        val parent = view.findViewById<ConstraintLayout>(R.id.constraintlayout)
        set.clone(parent) // parentLayout is a ConstraintLayout
        set.constrainPercentWidth(progressBar.id, value)
        set.constrainPercentHeight(progressBar.id, value)
        set.applyTo(parent)
    }

    private fun contentUpdate() {
        val text = "${progress} / ${progressMax} XP"
        contentTextView.text = text
    }

}