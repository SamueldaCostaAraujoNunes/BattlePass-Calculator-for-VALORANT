package br.com.battlepassCalculatorValorant.ui.view.fragment.Propriedades

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Observer.IObserver
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.model.Singleton.ManagerProperties
import br.com.battlepassCalculatorValorant.model.Util.ColorFromXml
import br.com.battlepassCalculatorValorant.ui.view.progressBar.mProgressBarView


class ExpStatisticFragment : Fragment(), IObserver {

    private lateinit var properties: Properties
    private lateinit var jogosNormais: mProgressBarView
    private lateinit var missoesDiarias: mProgressBarView
    private lateinit var missoesSemanais: mProgressBarView
    private lateinit var btnInfo: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        properties = ManagerProperties.getInstance(requireContext())
        properties.historic.add(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_exp_statistic, container, false)
        setupViews(view)
        setOnClickListeners()
        update()
        return view
    }

    @SuppressLint("SetTextI18n")
    private fun setOnClickListeners() {
        btnInfo.setOnClickListener {
            val dialog = AlertDialog
                .Builder(context, R.style.alertDialog)
//                .setCustomTitle(createTitle(requireContext().getString(R.string.info_exp_missoes)))
                .setTitle(requireContext().getString(R.string.info_exp_missoes))
                .setMessage(requireContext().getString(R.string.content_exp_missoes))
            dialog.show()
        }
    }

//    private fun createTitle(title: String): View {
//        val titleView: View = this.layoutInflater.inflate(R.layout.dialog_title, null)
//        titleView.title.text = title
//        return titleView
//    }

    private fun setupViews(v: View) {
        jogosNormais = v.findViewById(R.id.pb_jogos_normais)
        missoesDiarias = v.findViewById(R.id.pb_missoes_diarias)
        missoesSemanais = v.findViewById(R.id.pb_missoes_semanais)
        btnInfo = v.findViewById(R.id.img_btn_info)
    }

    private fun getColor(color: Int): Int {
        return Color.parseColor(ColorFromXml(requireContext()).getColor(color))
    }

    override fun update() {
        jogosNormais.setupProgress(
            requireContext().getString(R.string.exp_de_partidas),
            properties.expNormalGame(),
            properties.getNormalGame(),
            properties.getPercentNormalGame(),
            getColor(R.attr.colorSecondary)
        )

        missoesDiarias.setupProgress(
            requireContext().getString(R.string.missoes_diarias),
            properties.expMissaoDiaria().toInt(),
            properties.getExpMissaoDiaria(),
            properties.getPercentMissaoDiaria(),
            getColor(R.attr.colorAccent)
        )
        missoesSemanais.setupProgress(
            requireContext().getString(R.string.missoes_semanais),
            properties.expMissaoSemanal().toInt(),
            properties.getExpMissaoSemanal(),
            properties.getPercentMissaoSemanal(),
            getColor(R.attr.colorError)
        )
    }
}