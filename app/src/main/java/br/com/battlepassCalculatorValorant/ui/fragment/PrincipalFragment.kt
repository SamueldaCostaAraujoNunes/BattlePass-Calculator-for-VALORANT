package br.com.battlepassCalculatorValorant.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.model.SingletonPassBattle.ManagerProperties
import br.com.battlepassCalculatorValorant.ui.Advertisement.Advertisement.Companion.BANNER
import br.com.battlepassCalculatorValorant.ui.Advertisement.Advertisement.Companion.LARGE_BANNER
import br.com.battlepassCalculatorValorant.ui.Advertisement.Advertisement.Companion.MEDIUM_RECTANGLE
import kotlinx.android.synthetic.main.fragment_principal.*

class PrincipalFragment : Fragment() {
    private lateinit var properties: Properties

    override fun onCreate(savedInstanceState: Bundle?) {
        properties = ManagerProperties.getInstance(requireContext())
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adContainer0.createBanner(R.string.admob_card0, BANNER)
        adContainer1.createBanner(R.string.admob_card1, LARGE_BANNER)
        adContainer2.createBanner(R.string.admob_card2, MEDIUM_RECTANGLE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_principal, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        properties.historic.deleteAll()
    }
}