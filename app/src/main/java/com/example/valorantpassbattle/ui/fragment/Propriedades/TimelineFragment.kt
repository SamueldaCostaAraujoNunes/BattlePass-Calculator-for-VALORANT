package com.example.valorantpassbattle.ui.fragment.Propriedades

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.model.ColorFromXml
import com.example.valorantpassbattle.model.Observer.IObserver
import com.example.valorantpassbattle.model.Properties.Properties
import com.example.valorantpassbattle.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_timeline.*

class TimelineFragment : Fragment(), IObserver {
    private lateinit var properties: Properties
    private lateinit var colorGenerator: ColorFromXml
    override fun onCreate(savedInstanceState: Bundle?) {

        properties = MainActivity.Companion.properties
        properties.historic.add(this)
        colorGenerator = MainActivity.Companion.colorXML
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timeline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        update()
    }

    override fun update() {
        val dateInit = properties.passBattle.getDateInit()
        val dateFinally = properties.passBattle.getDateFinally()
        val finishForecast = properties.finishForecast()
        val daysForClosed = properties.daysMissing()!!

        tag_dias_adiantados_atrasados.hint =
            if (daysForClosed >= 0) "Dias Adiantados:" else "Dias Atrasados:"

        if (daysForClosed >= 0) {
            dias_adiantados_atrasados.setTextColor(Color.parseColor(colorGenerator.getColor(R.attr.colorAccent)))
        } else {
            dias_adiantados_atrasados.setTextColor(Color.parseColor(colorGenerator.getColor(R.attr.colorError)))
        }

        data_inicial_do_ato.text = dateInit
        data_final_do_ato.text = dateFinally
        data_finalizacao.text = finishForecast
        dias_adiantados_atrasados.text = daysForClosed.toString()
    }

}