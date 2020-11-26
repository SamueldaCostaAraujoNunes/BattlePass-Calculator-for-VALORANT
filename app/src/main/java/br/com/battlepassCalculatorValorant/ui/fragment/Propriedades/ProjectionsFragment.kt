package br.com.battlepassCalculatorValorant.ui.fragment.Propriedades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Observer.IObserver
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_projections.*

class ProjectionsFragment : Fragment(), IObserver {
    private lateinit var prop: Properties

    override fun onCreate(savedInstanceState: Bundle?) {
        prop = MainActivity.Companion.properties
        prop.historic.add(this)
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

        val jogosRestantesSC = prop.jogosRestantes(prop.semClassificacao)
        val jogosRestantesDS = prop.jogosRestantes(prop.disputaDeSpike)

        val tempoRestanteSC = prop.tempoRestante(prop.semClassificacao)
        val tempoRestanteDS = prop.tempoRestante(prop.disputaDeSpike)

        val jogosDiaSC = prop.jogosPorDia(prop.semClassificacao)
        val jogosDiaDS = prop.jogosPorDia(prop.disputaDeSpike)

        val horasDiasSC = prop.horasPorDia(prop.semClassificacao)
        val horasDiasDS = prop.horasPorDia(prop.disputaDeSpike)

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

}
