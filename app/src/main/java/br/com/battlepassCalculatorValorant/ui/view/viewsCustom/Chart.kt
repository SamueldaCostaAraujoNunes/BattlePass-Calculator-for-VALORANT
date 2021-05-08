package br.com.battlepassCalculatorValorant.ui.view.viewsCustom

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.extensions.getColorHexFromAttr
import com.github.aachartmodel.aainfographics.aachartcreator.*

class Chart @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var aaChartView: AAChartView = AAChartView(context)
    private val model = AAChartModel()
    private val hashMap: HashMap<String, List<Int>> = HashMap()

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
        model
            .chartType(AAChartType.Area)
            .tooltipValueSuffix(" EXP")
            .backgroundColor("#00000000")
            .yAxisTitle("EXP")
            .axesTextColor(
                context.getColorHexFromAttr(R.attr.colorOnPrimary)
            )
            .gradientColorEnable(true)
            .markerSymbol(AAChartSymbolType.Diamond)
            .colorsTheme(
                arrayOf(
                    context.getColorHexFromAttr(R.attr.colorSecondary),
                    context.getColorHexFromAttr(R.attr.colorAccent),
                    context.getColorHexFromAttr(R.attr.colorError)
                )
            )
    }

    fun setData(label: String, content: List<Int>) {
        hashMap[label] = content
    }

    fun show() {
        val series = ArrayList<AASeriesElement>()
        for ((key, content) in hashMap) {
            val element = createElement(key, content)
            series.add(element)
        }
        model.series(series.toTypedArray())
        aaChartView.aa_drawChartWithChartModel(model)
    }

    private fun createElement(label: String, data: List<Int>): AASeriesElement {
        return AASeriesElement()
            .name(label)
            .data(data.toTypedArray())
    }
}