package com.example.valorantpassbattle.model.Charts

import android.util.Log
import com.example.valorantpassbattle.model.Observer.IObserver
import com.github.aachartmodel.aainfographics.aachartcreator.*

class Charts(val chartView: AAChartView) {
    val model = AAChartModel()
    private val series = ArrayList<AASeriesElement>()
    private val labels = ArrayList<String>()

    init {
        createModel()
    }

    fun createModel(){
        model
        .chartType(AAChartType.Area)
        .tooltipValueSuffix(" Xp")
        .title("Seu Passe de Batalha")
        .subtitle("passBattle")
        .backgroundColor("#4b2b7f")
        .dataLabelsEnabled(false)
        .markerSymbol(AAChartSymbolType.Diamond)
        .colorsTheme(arrayOf("#fe117c", "#ffc069", "#06caf4", "#7dffc0"))
    }

    fun setData(label: String, data: ArrayList<Int>){
        if(label in labels){
            val index = labels.indexOf(label)
            series.removeAt(index)
            labels.removeAt(index)}
        series.add(AASeriesElement()
            .name(label)
            .data(data.toArray()))
        labels.add(label)
    }

    fun show(){
        model.series(series.toTypedArray())
        chartView.aa_drawChartWithChartModel(model)
    }
}