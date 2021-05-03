package br.com.battlepassCalculatorValorant.ui.view.fragment.Infos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Observer.IObserver
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.model.Singleton.ManagerProperties
import kotlinx.android.synthetic.main.fragment_projections.*

class ProjectionsFragment : Fragment(), IObserver {
    private lateinit var properties: Properties

    override fun onCreate(savedInstanceState: Bundle?) {
        properties = ManagerProperties.getInstance(requireContext())
        properties.historic.add(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_projections, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        update()
    }

    override fun update() {

        val jogosRestantesSC = properties.jogosRestantes(properties.semClassificacao)
        val jogosRestantesDS = properties.jogosRestantes(properties.disputaDeSpike)

        val tempoRestanteSC = properties.tempoRestante(properties.semClassificacao)
        val tempoRestanteDS = properties.tempoRestante(properties.disputaDeSpike)

        val jogosDiaSC = properties.jogosPorDia(properties.semClassificacao)
        val jogosDiaDS = properties.jogosPorDia(properties.disputaDeSpike)

        val horasDiasSC = properties.horasPorDia(properties.semClassificacao)
        val horasDiasDS = properties.horasPorDia(properties.disputaDeSpike)

        //Jogos Restantes
        tv_jogos_restantes_sc.text = jogosRestantesSC.toInt().toString()
        tv_jogos_restantes_ds.text = jogosRestantesDS.toInt().toString()

        //Tempo Restante
        tv_tempo_restante_sc.text = convertHours(tempoRestanteSC)
        tv_tempo_restante_ds.text = convertHours(tempoRestanteDS)

        //Jogos Por Dia
        tv_jogos_dia_sc.text = jogosDiaSC.toString()
        tv_jogos_dia_ds.text = jogosDiaDS.toString()

        //Horas por dia
        tv_horas_dia_sc.text = convertHours(horasDiasSC)
        tv_horas_dia_ds.text = convertHours(horasDiasDS)
    }

    private fun convertHours(time: Float): String {
        val hours = time.toInt()
        val minutes = ((time % 1) * 60).toInt()
        return "${hours}:${minutes} Hrs"
    }

    override fun toString(): String {
        return context?.getString(R.string.expectativas) ?: super.toString()
    }
}
