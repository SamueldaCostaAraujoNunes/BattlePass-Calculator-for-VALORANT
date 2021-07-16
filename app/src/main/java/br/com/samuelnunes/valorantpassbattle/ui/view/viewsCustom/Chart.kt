package br.com.samuelnunes.valorantpassbattle.ui.view.viewsCustom

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.extensions.getColorHexFromAttr
import com.github.aachartmodel.aainfographics.aachartcreator.*

class Chart @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var aaChartView: AAChartView = AAChartView(context)
    private val model = AAChartModel()
    private val hashMap: HashMap<String, AASeriesElement> = HashMap()

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
            .backgroundColor(context.getColorHexFromAttr(R.attr.colorPrimary))
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
        hashMap[label] = createElement(label, content)
        model.series(hashMap.values.toTypedArray())
    }

    fun show() {
        aaChartView.aa_drawChartWithChartModel(model)
        aaChartView.aa_onlyRefreshTheChartDataWithChartOptionsSeriesArray(hashMap.values.toTypedArray())
    }

    private fun createElement(label: String, data: List<Int>): AASeriesElement {
        return AASeriesElement()
            .name(label)
            .data(data.toTypedArray())
    }
}