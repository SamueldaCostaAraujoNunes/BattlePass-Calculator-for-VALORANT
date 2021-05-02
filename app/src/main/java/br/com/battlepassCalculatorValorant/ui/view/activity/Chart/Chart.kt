package br.com.battlepassCalculatorValorant.ui.view.activity.Chart

import android.content.Context
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Observer.IObserver
import br.com.battlepassCalculatorValorant.model.Util.ColorFromXml
import com.github.aachartmodel.aainfographics.aachartcreator.*


class Chart(val context: Context, val chartView: AAChartView) : IObserver {
    val colorGenerator = ColorFromXml(context)
    val model = AAChartModel()
    private var series = ArrayList<AASeriesElement>()
    private val functionsSeries = ArrayList<() -> ArrayList<Int>>()
    private val labels = ArrayList<String>()

    init {
        createModel()
    }

    fun createModel(){
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

    fun setData(label: String, function: () -> ArrayList<Int>){
        if(label in labels){
            val index = labels.indexOf(label)
            functionsSeries.removeAt(index)
            labels.removeAt(index)}
        functionsSeries.add(function)
        labels.add(label)
    }

    fun show(){
        series = ArrayList()
        for (index in 0 until functionsSeries.size) {
            val label = labels[index]
            val function = functionsSeries[index]
            val element = createElement(label, function())
            series.add(element)
        }
        model.series(series.toTypedArray())
        chartView.aa_drawChartWithChartModel(model)
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