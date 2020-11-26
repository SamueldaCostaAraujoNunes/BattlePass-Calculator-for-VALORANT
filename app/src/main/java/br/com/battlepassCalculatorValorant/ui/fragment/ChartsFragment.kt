package br.com.battlepassCalculatorValorant.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.model.Advertisement.Advertisement
import br.com.battlepassCalculatorValorant.model.Properties.Properties
import br.com.battlepassCalculatorValorant.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_charts.*

class ChartsFragment : Fragment() {
    private lateinit var properties: Properties
    private lateinit var adv: Advertisement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        properties = MainActivity.properties
        adv = Advertisement(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_charts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adv.createBanner(adContainer3, R.string.admob_card3, Advertisement.BANNER)
        adv.createBanner(adContainer4, R.string.admob_card4, Advertisement.MEDIUM_RECTANGLE)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        properties.historic.deleteAll()
    }
}