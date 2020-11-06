package com.example.valorantpassbattle.ui.activity.Chart

import android.content.Context
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.model.ColorFromXml
import com.example.valorantpassbattle.model.Historic.Historic
import com.example.valorantpassbattle.model.Observer.IObserver
import com.github.aachartmodel.aainfographics.aachartcreator.*
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAStyle


open class Chart(val context: Context, val chartView: AAChartView): IObserver {
    val colorGenerator = ColorFromXml(context)
    val model = AAChartModel()
    private var series = ArrayList<AASeriesElement>()
    private val functionsSeries = ArrayList<() -> ArrayList<Int>>()
    private val labels = ArrayList<String>()

    init {
        createModel()
    }

    fun createModel(){
        val backgroundColor = colorGenerator.getColor(R.color.valorant_dark1)
        val colorPrimary = colorGenerator.getColor(R.color.valorant_primary)
        val white = colorGenerator.getColor(R.color.valorant_light)
        val colorsTheme = model
            .chartType(AAChartType.Area)
            .tooltipValueSuffix(" Xp")
            .title("Seu Passe de Batalha")
            .titleStyle(AAStyle()
                .color(white)
                .fontSize(14F))
            .backgroundColor(backgroundColor)
            .yAxisTitle("XP")
            .axesTextColor(white)
            .gradientColorEnable(true)
            .markerSymbol(AAChartSymbolType.Diamond)
            .colorsTheme(arrayOf(colorPrimary, "#ffc069", "#06caf4", "#7dffc0"))
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
        for(index in 0 until functionsSeries.size){
            val label = labels[index]
            val function = functionsSeries[index]
            val element = createElement(label, function())
            series.add(element)
        }
        model.series(series.toTypedArray())
        chartView.aa_drawChartWithChartModel(model)
    }

    fun createElement(label: String, data: ArrayList<Int>): AASeriesElement {
        val element = AASeriesElement()
            .name(label)
            .data(data.toArray())
        return element
    }

    override fun update() {
        show()
    }
}