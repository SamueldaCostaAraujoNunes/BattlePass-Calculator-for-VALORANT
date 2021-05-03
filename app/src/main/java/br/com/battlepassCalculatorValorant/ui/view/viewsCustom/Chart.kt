package br.com.battlepassCalculatorValorant.ui.view.viewsCustom

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Observer.IObserver
import br.com.battlepassCalculatorValorant.model.Util.ColorFromXml
import com.github.aachartmodel.aainfographics.aachartcreator.*

class Chart @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), IObserver {

    private var aaChartView: AAChartView = AAChartView(context)
    private val colorGenerator = ColorFromXml(context)
    private val model = AAChartModel()
    private var series = ArrayList<AASeriesElement>()
    private val functionsSeries = ArrayList<() -> ArrayList<Int>>()
    private val labels = ArrayList<String>()

    init {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        orientation = VERTICAL

        aaChartView.layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        addView(aaChartView)
        createModel()
    }

    private fun createModel() {
        val colorOnPrimary = colorGenerator.getColor(R.attr.colorOnPrimary)
        val colorSecondary = colorGenerator.getColor(R.attr.colorSecondary)
        val colorAccent = colorGenerator.getColor(R.attr.colorAccent)
        val colorError = colorGenerator.getColor(R.attr.colorError)
        model
            .chartType(AAChartType.Area)
            .tooltipValueSuffix(" EXP")
            .backgroundColor("#00000000")
            .yAxisTitle("EXP")
            .axesTextColor(colorOnPrimary)
            .gradientColorEnable(true)
            .markerSymbol(AAChartSymbolType.Diamond)
            .colorsTheme(arrayOf(colorSecondary, colorAccent, colorError, "#7dffc0"))
    }

    fun setData(label: String, function: () -> ArrayList<Int>) {
        if (label in labels) {
            val index = labels.indexOf(label)
            functionsSeries.removeAt(index)
            labels.removeAt(index)
        }
        functionsSeries.add(function)
        labels.add(label)
    }

    fun show() {
        series = ArrayList()
        for (index in 0 until functionsSeries.size) {
            val label = labels[index]
            val function = functionsSeries[index]
            val element = createElement(label, function())
            series.add(element)
        }
        model.series(series.toTypedArray())
        aaChartView.aa_drawChartWithChartModel(model)
    }

    private fun createElement(label: String, data: ArrayList<Int>): AASeriesElement {
        return AASeriesElement()
            .name(label)
            .data(data.toArray())
    }

    override fun update() {
        show()
    }
}