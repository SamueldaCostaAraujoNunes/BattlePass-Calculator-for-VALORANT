package br.com.battlepassCalculatorValorant.ui.fragment.Propriedades

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.ColorFromXml
import br.com.battlepassCalculatorValorant.model.Observer.IObserver
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.model.SingletonPassBattle.ManagerColorFromXml
import br.com.battlepassCalculatorValorant.model.SingletonPassBattle.ManagerProperties
import kotlinx.android.synthetic.main.fragment_timeline.*
import kotlin.math.absoluteValue

class TimelineFragment : Fragment(), IObserver {
    private lateinit var properties: Properties
    private lateinit var colorGenerator: ColorFromXml
    override fun onCreate(savedInstanceState: Bundle?) {
        properties = ManagerProperties.getInstance(requireContext())
        properties.historic.add(this)
        colorGenerator = ManagerColorFromXml.getInstance(requireContext())
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
        val daysMissing = properties.daysMissing()!!
        val dayCurrent = properties.dayCurrent()
        val daysForClosed = properties.daysForClosed()

        tag_dias_adiantados_atrasados.hint =
            if (daysMissing >= 0)
                requireContext().getString(R.string.dias_adiantados)
            else
                requireContext().getString(R.string.dias_atrasados)

        if (daysMissing >= 0) {
            val colorAccent = Color.parseColor(colorGenerator.getColor(R.attr.colorAccent))
            dias_adiantados_atrasados.setTextColor(colorAccent)
            data_finalizacao.setTextColor(colorAccent)
        } else {
            val colorError = Color.parseColor(colorGenerator.getColor(R.attr.colorError))
            dias_adiantados_atrasados.setTextColor(colorError)
            data_finalizacao.setTextColor(colorError)
        }

        data_inicial_do_ato.text = dateInit
        data_final_do_ato.text = dateFinally
        data_finalizacao.text = finishForecast
        dia_do_passe.text = dayCurrent.toString()
        dias_para_acabar.text = daysForClosed.toString()
        dias_adiantados_atrasados.text = daysMissing.absoluteValue.toString()
    }

}